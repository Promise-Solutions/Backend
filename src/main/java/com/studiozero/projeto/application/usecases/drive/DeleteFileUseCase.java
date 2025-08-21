package com.studiozero.projeto.application.usecases.drive;

import com.studiozero.projeto.domain.repositories.DriveRepository;

public class DeleteFileUseCase {
    private final DriveRepository driveRepository;

    public DeleteFileUseCase(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public void execute(String fileId) {
        driveRepository.deleteFile(fileId);
    }
}
