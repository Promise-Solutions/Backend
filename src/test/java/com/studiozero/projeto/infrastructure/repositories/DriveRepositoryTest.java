package com.studiozero.projeto.infrastructure.repositories;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.studiozero.projeto.infrastructure.repositories.Implements.DriveRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriveRepositoryTest {

    @Mock
    private Drive drive;

    @Mock
    private Drive.Files driveFiles;

    @Mock
    private Drive.Files.Create driveFilesCreate;

    @Mock
    private Drive.Files.List driveFilesList;

    @Mock
    private Drive.Files.Delete driveFilesDelete;

    @Mock
    private Drive.Files.Get driveFilesGet;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private DriveRepositoryImpl driveRepository;

    @Test
    @DisplayName("Should upload file using MultipartFile")
    void shouldUploadFileMultipart() throws Exception {
        byte[] fakeContent = "abc".getBytes();
        InputStream inputStream = new ByteArrayInputStream(fakeContent);

        when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        when(multipartFile.getContentType()).thenReturn("text/plain");
        when(multipartFile.getInputStream()).thenReturn(inputStream);

        File uploaded = new File();
        uploaded.setId("123");

        when(drive.files()).thenReturn(driveFiles);
        when(driveFiles.create(any(File.class), any(InputStreamContent.class)))
                .thenReturn(driveFilesCreate);
        when(driveFilesCreate.setFields(any())).thenReturn(driveFilesCreate);
        when(driveFilesCreate.execute()).thenReturn(uploaded);

        String result = driveRepository.uploadFile(multipartFile);

        assertEquals("123", result);
        verify(driveFilesCreate).execute();
    }

    @Test
    @DisplayName("Should upload file using InputStream")
    void shouldUploadFileStream() throws Exception {
        InputStream inputStream = new ByteArrayInputStream("content".getBytes());

        File uploaded = new File();
        uploaded.setId("999");

        when(drive.files()).thenReturn(driveFiles);
        when(driveFiles.create(any(File.class), any(InputStreamContent.class)))
                .thenReturn(driveFilesCreate);
        when(driveFilesCreate.setFields(any())).thenReturn(driveFilesCreate);
        when(driveFilesCreate.execute()).thenReturn(uploaded);

        String result = driveRepository.uploadFileStream("file.txt", "text/plain", inputStream);

        assertEquals("999", result);
        verify(driveFilesCreate).execute();
    }

    @Test
    @DisplayName("Should delete a file")
    void shouldDeleteFile() throws Exception {
        when(drive.files()).thenReturn(driveFiles);
        when(driveFiles.delete("123")).thenReturn(driveFilesDelete);

        driveRepository.deleteFile("123");

        verify(driveFilesDelete).execute();
    }

    @Test
    @DisplayName("Should throw RuntimeException when upload multipart fails")
    void shouldThrowExceptionUploadFile() throws Exception {
        when(multipartFile.getInputStream()).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> driveRepository.uploadFile(multipartFile));
    }

    @Test
    @DisplayName("Should throw RuntimeException when list fails")
    void shouldThrowExceptionListFiles() throws Exception {
        when(drive.files()).thenReturn(driveFiles);
        when(driveFiles.list()).thenReturn(driveFilesList);
        when(driveFilesList.setFields(any())).thenReturn(driveFilesList);
        when(driveFilesList.setPageToken(any())).thenReturn(driveFilesList);
        when(driveFilesList.execute()).thenThrow(new IOException());

        assertThrows(RuntimeException.class, () -> driveRepository.listFiles());
    }
}
