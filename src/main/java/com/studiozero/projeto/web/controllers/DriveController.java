package com.studiozero.projeto.web.controllers;

import com.google.api.services.drive.model.File;
import com.studiozero.projeto.application.services.DriveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/drive")
@RequiredArgsConstructor
@Tag(name = "Google Drive API", description = "Endpoints for Google Drive")
public class DriveController {

    private final DriveService driveService;

    @Operation(
            summary = "Sent a file to drive",
            description = "This method is responsible for sent a file to drive."
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") MultipartFile file
    ) {
        try {
            String fileId = driveService.uploadFile(file);
            return ResponseEntity.ok("File sent successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload error: " + e.getMessage());
        }
    }

    @Operation(
            summary = "List files from drive",
            description = "This method is responsible for list files from drive."
    )
    @GetMapping
    public ResponseEntity<List<File>> listFiles() {
        try {
            List<File> files = driveService.listFiles();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Delete file from drive",
            description = "This method is responsible for deleting a file from drive by its ID."
    )
    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        try {
            driveService.deleteFile(fileId);
            return ResponseEntity.ok("File deleted successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete error: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Download file from drive",
            description = "This method is responsible for downloading a file from drive by its ID."
    )
    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
        try {
            byte[] fileData = driveService.downloadFile(fileId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileId + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}