package com.studiozero.projeto.application.usecases.drive;

import com.studiozero.projeto.domain.repositories.DriveRepository;

public class UploadFileUseCase {
    private final DriveRepository driveRepository;

    public UploadFileUseCase(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public String execute(Object multipartFile) {
        return driveRepository.uploadFile(multipartFile);
    }
}
