package com.studiozero.projeto.domain.repositories;

import org.springframework.web.multipart.MultipartFile;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.util.List;

public interface DriveRepository {
    String uploadFile(MultipartFile multipartFile) throws IOException;
    String uploadFileStream(String fileName, String contentType, java.io.InputStream inputStream) throws IOException;
    List<File> listFiles() throws IOException;
    void deleteFile(String fileId) throws IOException;
    byte[] downloadFile(String fileId) throws IOException;
}
