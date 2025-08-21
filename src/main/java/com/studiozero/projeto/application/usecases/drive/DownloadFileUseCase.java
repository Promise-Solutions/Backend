package com.studiozero.projeto.application.usecases.drive;

import com.studiozero.projeto.domain.repositories.DriveRepository;

public class DownloadFileUseCase {
    private final DriveRepository driveRepository;

    public DownloadFileUseCase(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public byte[] execute(String fileId) {
        return driveRepository.downloadFile(fileId);
    }
}
