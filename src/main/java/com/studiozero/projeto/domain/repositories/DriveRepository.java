package com.studiozero.projeto.domain.repositories;

import java.util.List;

public interface DriveRepository {
    String uploadFile(Object multipartFile);

    String uploadFileStream(String fileName, String contentType, java.io.InputStream inputStream);

    List<Object> listFiles();

    void deleteFile(String fileId);

    byte[] downloadFile(String fileId);
}
