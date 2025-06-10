package com.studiozero.projeto.services;

import static org.junit.jupiter.api.Assertions.*;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriveServiceTest {

    @Mock
    private Drive drive;

    @Mock
    private Drive.Files driveFiles;

    @Mock
    private Drive.Files.Create create;

    @Mock
    private Drive.Files.List list;

    @Mock
    private Drive.Files.Get get;

    @Mock
    private Drive.Files.Delete delete;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private DriveService driveService;

    @BeforeEach
    void setup() throws Exception {
        when(drive.files()).thenReturn(driveFiles);
    }

    @Test
    @DisplayName("Should upload file from MultipartFile")
    void testUploadFile() throws Exception {
        File uploadedFile = new File();
        uploadedFile.setId("abc123");

        when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        when(multipartFile.getContentType()).thenReturn("text/plain");
        when(multipartFile.getInputStream()).thenReturn(new ByteArrayInputStream("content".getBytes()));

        when(driveFiles.create(any(File.class), any(InputStreamContent.class))).thenReturn(create);
        when(create.setFields("id, parents")).thenReturn(create);
        when(create.execute()).thenReturn(uploadedFile);

        String fileId = driveService.uploadFile(multipartFile);
        assertEquals("abc123", fileId);
    }

    @Test
    @DisplayName("Should upload file from InputStream")
    void testUploadFileStream() throws Exception {
        File uploadedFile = new File();
        uploadedFile.setId("xyz789");

        InputStream inputStream = new ByteArrayInputStream("hello world".getBytes());

        when(driveFiles.create(any(File.class), any(InputStreamContent.class))).thenReturn(create);
        when(create.setFields("id, parents")).thenReturn(create);
        when(create.execute()).thenReturn(uploadedFile);

        String fileId = driveService.uploadFileStream("file.txt", "text/plain", inputStream);
        assertEquals("xyz789", fileId);
    }

    @Test
    @DisplayName("Should list files from Google Drive")
    void testListFiles() throws Exception {
        File file1 = new File();
        file1.setId("1");
        file1.setName("File1");

        File file2 = new File();
        file2.setId("2");
        file2.setName("File2");

        FileList fileList = new FileList();
        fileList.setFiles(List.of(file1, file2));
        fileList.setNextPageToken(null);

        when(driveFiles.list()).thenReturn(list);
        when(list.setFields(anyString())).thenReturn(list);
        when(list.setPageToken(any())).thenReturn(list);
        when(list.execute()).thenReturn(fileList);

        List<File> files = driveService.listFiles();
        assertEquals(2, files.size());
        assertEquals("File1", files.get(0).getName());
        assertEquals("File2", files.get(1).getName());
    }

    @Test
    @DisplayName("Should delete file from Google Drive")
    void testDeleteFile() throws Exception {
        when(driveFiles.delete("fileId")).thenReturn(delete);
        driveService.deleteFile("fileId");
        verify(delete).execute();
    }

    @Test
    @DisplayName("Should download file from Google Drive")
    void testDownloadFile() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write("data".getBytes());

        doAnswer(invocation -> {
            ByteArrayOutputStream out = invocation.getArgument(0);
            out.write("data".getBytes());
            return null;
        }).when(get).executeMediaAndDownloadTo(any(ByteArrayOutputStream.class));

        when(driveFiles.get("fileId")).thenReturn(get);

        byte[] result = driveService.downloadFile("fileId");
        assertArrayEquals("data".getBytes(), result);
    }
}
