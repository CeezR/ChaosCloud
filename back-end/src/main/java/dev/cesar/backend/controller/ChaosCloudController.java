package dev.cesar.backend.controller;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.model.MediaFileRequestDTO;
import dev.cesar.backend.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChaosCloudController {
    private final MediaFileService service;

    @Autowired
    public ChaosCloudController(MediaFileService service) {
        this.service = service;
    }

    @PostMapping(path = "/files")
    public ResponseEntity<MediaFile> uploadFile(@RequestBody MediaFileRequestDTO requestDTO) {
        try {
            MediaFile mediaFile = service.store(requestDTO.fileName(), requestDTO.fileContent());
            return ResponseEntity.ok(mediaFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
