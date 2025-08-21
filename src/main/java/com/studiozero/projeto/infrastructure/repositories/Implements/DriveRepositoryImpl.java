package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.repositories.DriveRepository;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Repository
public class DriveRepositoryImpl implements DriveRepository {

    private final Drive drive;

    public DriveRepositoryImpl(@Qualifier("customGoogleDriveService") Drive drive) {
        this.drive = drive;
    }

    @Override
    public String uploadFile(Object multipartFile) {
        try {
            MultipartFile file = (MultipartFile) multipartFile;
            String folderId = "1TA3cV-P-lzcgwRiIZuqoEunEPF32dGQa";
            File fileMetadata = new File();
            fileMetadata.setName(file.getOriginalFilename());
            fileMetadata.setParents(List.of(folderId));
            File uploadedFile = drive.files().create(fileMetadata,
                    new com.google.api.client.http.InputStreamContent(
                            file.getContentType(),
                            file.getInputStream()))
                    .setFields("id, parents")
                    .execute();
            return uploadedFile.getId();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload para o Drive", e);
        }
    }

    @Override
    public String uploadFileStream(String fileName, String contentType, InputStream inputStream) {
        try {
            String folderId = "1TA3cV-P-lzcgwRiIZuqoEunEPF32dGQa";
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
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload para o Drive", e);
        }
    }

    @Override
    public List<Object> listFiles() {
        List<Object> allFiles = new ArrayList<>();
        String pageToken = null;
        try {
            do {
                FileList result = drive.files().list()
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();
                allFiles.addAll(result.getFiles());
                pageToken = result.getNextPageToken();
            } while (pageToken != null);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar arquivos do Drive", e);
        }
        return allFiles;
    }

    @Override
    public void deleteFile(String fileId) {
        try {
            drive.files().delete(fileId).execute();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar arquivo do Drive", e);
        }
    }

    @Override
    public byte[] downloadFile(String fileId) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            drive.files().get(fileId).executeMediaAndDownloadTo(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao baixar arquivo do Drive", e);
        }
    }
}
