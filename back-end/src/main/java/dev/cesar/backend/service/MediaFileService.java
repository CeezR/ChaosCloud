package dev.cesar.backend.service;

import dev.cesar.backend.MediaFile;
import dev.cesar.backend.repository.MediaFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaFileService {
    private final MediaFileRepository repo;
    private final Path staticPath = Paths.get("src/main/resources/static");

    public MediaFileService(MediaFileRepository repo) {
        this.repo = repo;
    }

    public Path store(String fileName, byte[] content) throws IOException {
        Path filePath = staticPath.resolve(fileName);
        Files.write(filePath, content);
        repo.save(new MediaFile());
        return filePath;
    }

    public byte[] read(String fileName) throws IOException {
        Path filePath = staticPath.resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    public void delete(String fileName) throws IOException {
        Path filePath = staticPath.resolve(fileName);
        Files.delete(filePath);
    }

    public boolean exists(String fileName) {
        Path filePath = staticPath.resolve(fileName);
        return Files.exists(filePath);
    }
}
