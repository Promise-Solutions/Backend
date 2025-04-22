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
        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());

        File uploadedFile = drive.files().create(fileMetadata,
                        new com.google.api.client.http.InputStreamContent(
                                multipartFile.getContentType(),
                                multipartFile.getInputStream()
                        ))
                .setFields("id")
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