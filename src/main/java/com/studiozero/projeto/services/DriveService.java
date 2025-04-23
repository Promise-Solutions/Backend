package com.studiozero.projeto.services;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriveService {

    private final Drive drive;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String folderId = "1fp9toLbiE19A72UWv-Cs4giiFqkGKSQ2"; // ID da pasta no Google Drive (trocar para a do cliente)

        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());
        fileMetadata.setParents(List.of(folderId));

        File uploadedFile = drive.files().create(fileMetadata,
                        new com.google.api.client.http.InputStreamContent(
                                multipartFile.getContentType(),
                                multipartFile.getInputStream()
                        ))
                .setFields("id, parents")
                .execute();

        return uploadedFile.getId();
    }


    public List<File> listFiles() throws IOException {
        FileList result = drive.files().list()
                .setPageSize(10)
                .setFields("files(id, name)")
                .execute();

        return result.getFiles();
    }
}