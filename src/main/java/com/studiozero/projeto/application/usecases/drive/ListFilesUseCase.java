package com.studiozero.projeto.application.usecases.drive;

import com.studiozero.projeto.domain.repositories.DriveRepository;
import java.util.List;

public class ListFilesUseCase {
    private final DriveRepository driveRepository;

    public ListFilesUseCase(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public List<Object> execute() {
        return driveRepository.listFiles();
    }
}
