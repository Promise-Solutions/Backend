package com.studiozero.projeto.infrastructure.repositories;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.studiozero.projeto.infrastructure.repositories.services.DriveServiceRepository;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DriveServiceRepositoryTest {

    @Test
    void testUploadFileStreamSuccess() throws Exception {
        Drive driveMock = mock(Drive.class, RETURNS_DEEP_STUBS);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("conteudo".getBytes());

        File fileMock = new File();
        fileMock.setId("456");
        when(driveMock.files().create(any(File.class), any())).thenReturn(mock(Drive.Files.Create.class));
        when(driveMock.files().create(any(File.class), any())
                .setFields(anyString())
                .execute()).thenReturn(fileMock);

        DriveServiceRepository repo = new DriveServiceRepository(driveMock);
        String fileId = repo.uploadFileStream("arquivo.txt", "text/plain", inputStream);

        assertEquals("456", fileId);
    }

    @Test
    void testDownloadFileSuccess() throws Exception {
        Drive driveMock = mock(Drive.class, RETURNS_DEEP_STUBS);
        Drive.Files.Get getMock = mock(Drive.Files.Get.class);
        when(driveMock.files().get(anyString())).thenReturn(getMock);

        doAnswer(invocation -> {
            ByteArrayOutputStream out = invocation.getArgument(0);
            out.write("conteudo".getBytes());
            return null;
        }).when(getMock).executeMediaAndDownloadTo(any(ByteArrayOutputStream.class));

        DriveServiceRepository repo = new DriveServiceRepository(driveMock);
        byte[] result = repo.downloadFile("fileId");

        assertArrayEquals("conteudo".getBytes(), result);
    }
}