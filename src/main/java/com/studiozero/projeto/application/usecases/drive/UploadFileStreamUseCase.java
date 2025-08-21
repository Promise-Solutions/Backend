package com.studiozero.projeto.application.usecases.drive;

import com.studiozero.projeto.domain.repositories.DriveRepository;

public class UploadFileStreamUseCase {
    private final DriveRepository driveRepository;

    public UploadFileStreamUseCase(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public String execute(String fileName, String contentType, java.io.InputStream inputStream) {
        return driveRepository.uploadFileStream(fileName, contentType, inputStream);
    }
}
