package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.drive.UploadFileUseCase;
import com.studiozero.projeto.application.usecases.drive.ListFilesUseCase;
import com.studiozero.projeto.application.usecases.drive.DeleteFileUseCase;
import com.studiozero.projeto.application.usecases.drive.DownloadFileUseCase;
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

    private final UploadFileUseCase uploadFileUseCase;
    private final ListFilesUseCase listFilesUseCase;
    private final DeleteFileUseCase deleteFileUseCase;
    private final DownloadFileUseCase downloadFileUseCase;

    @Operation(summary = "Sent a file to drive", description = "This method is responsible for sent a file to drive.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") MultipartFile file) {
        try {
            String fileId = uploadFileUseCase.execute(file);
            return ResponseEntity.ok("File sent successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload error: " + e.getMessage());
        }
    }

    @Operation(summary = "List files from drive", description = "This method is responsible for list files from drive.")
    @GetMapping
    public ResponseEntity<List<Object>> listFiles() {
        try {
            var files = listFilesUseCase.execute();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete file from drive", description = "This method is responsible for deleting a file from drive by its ID.")
    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileId) {
        try {
            deleteFileUseCase.execute(fileId);
            return ResponseEntity.ok("File deleted successfully! ID: " + fileId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete error: " + e.getMessage());
        }
    }

    @Operation(summary = "Download file from drive", description = "This method is responsible for downloading a file from drive by its ID.")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
        try {
            byte[] fileData = downloadFileUseCase.execute(fileId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileId + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}