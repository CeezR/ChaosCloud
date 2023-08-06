package dev.cesar.backend.controller;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.model.MediaFileRequestDTO;
import dev.cesar.backend.repository.MediaFileDBRepository;
import dev.cesar.backend.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChaosCloudController {
    private final MediaFileService service;
    private final MediaFileDBRepository repository;

    @Autowired
    public ChaosCloudController(MediaFileService service, MediaFileDBRepository repository) {
        this.service = service;
        this.repository = repository;
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

    @GetMapping(path = "/files")
    public ResponseEntity<List<MediaFile>> getAllFiles() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/files/{id}")
    public ResponseEntity<MediaFileRequestDTO> downloadFile(@PathVariable Long id) {
        MediaFile mediaFile = repository.findById(id);
        if(mediaFile == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileContent;
        try {
            fileContent = service.read(mediaFile.getFilePath());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(new MediaFileRequestDTO(mediaFile.getFileName(), fileContent));
    }
}
