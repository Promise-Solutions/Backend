package com.studiozero.projeto.controllers;

import com.google.api.services.drive.model.File;
import com.studiozero.projeto.services.DriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/drive")
@RequiredArgsConstructor
public class DriveController {

    private final DriveService driveService;

    @PostMapping
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String fileId = driveService.uploadFile(file);
            return ResponseEntity.ok("File sent successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<File>> listFiles() {
        try {
            List<File> files = driveService.listFiles();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}