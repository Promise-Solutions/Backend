package com.studiozero.projeto.infrastructure.repositories.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.studiozero.projeto.domain.repositories.DriveRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DriveServiceRepository implements DriveRepository {
    @Qualifier("customGoogleDriveService")
    private final Drive drive;

    public DriveServiceRepository(Drive drive) {
        this.drive = drive;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String folderId = "1TA3cV-P-lzcgwRiIZuqoEunEPF32dGQa"; // ID da pasta no Google Drive (trocar para a
        // do cliente)

        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());
        fileMetadata.setParents(List.of(folderId));

        File uploadedFile = drive.files().create(fileMetadata,
                        new com.google.api.client.http.InputStreamContent(
                                multipartFile.getContentType(),
                                multipartFile.getInputStream()))
                .setFields("id, parents")
                .execute();

        return uploadedFile.getId();
    }

    @Override
    public String uploadFileStream(String fileName, String contentType, java.io.InputStream inputStream)
            throws IOException {
        String folderId = "1TA3cV-P-lzcgwRiIZuqoEunEPF32dGQa"; // ID da pasta no Google Drive

        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(List.of(folderId));

        File uploadedFile = drive.files().create(fileMetadata,
                        new com.google.api.client.http.InputStreamContent(
                                contentType,
                                inputStream))
                .setFields("id, parents")
                .execute();

        return uploadedFile.getId();
    }

    @Override
    public List<File> listFiles() throws IOException {
        List<File> allFiles = new java.util.ArrayList<>();
        String pageToken = null;

        do {
            FileList result = drive.files().list()
                    .setFields("nextPageToken, files(id, name)")
                    .setPageToken(pageToken)
                    .execute();

            allFiles.addAll(result.getFiles());
            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        return allFiles;
    }

    @Override
    public void deleteFile(String fileId) throws IOException {
        drive.files().delete(fileId).execute();
    }

    @Override
    public byte[] downloadFile(String fileId) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        drive.files().get(fileId).executeMediaAndDownloadTo(outputStream);
        return outputStream.toByteArray();
    }
}
