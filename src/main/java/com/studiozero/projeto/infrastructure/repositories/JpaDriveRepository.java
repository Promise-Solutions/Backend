package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.repositories.DriveRepository;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaDriveRepository implements DriveRepository {
    @Override
    public List<Object> listFiles() {
        try {
            String folderId = "1TA3cV-P-lzcgwRiIZuqoEunEPF32dGQa";
            String query = "'" + folderId + "' in parents and trashed = false";
            List<File> files = drive.files().list()
                    .setQ(query)
                    .setFields("files(id, name, mimeType, parents)")
                    .execute()
                    .getFiles();
            return new java.util.ArrayList<>(files);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar arquivos do Drive", e);
        }
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
        try (java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream()) {
            drive.files().get(fileId).executeMediaAndDownloadTo(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao baixar arquivo do Drive", e);
        }
    }

    @Qualifier("customGoogleDriveService")
    private final Drive drive;

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
    public String uploadFileStream(String fileName, String contentType, java.io.InputStream inputStream) {
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
}
