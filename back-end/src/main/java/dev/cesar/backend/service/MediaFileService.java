package dev.cesar.backend.service;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.repository.MediaFileDBRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaFileService {
    private final MediaFileDBRepository repo;
    private final Path staticPath = Paths.get("src/main/resources/static");

    public MediaFileService(MediaFileDBRepository repo) {
        this.repo = repo;
    }

    public MediaFile store(String fileName, byte[] content) throws IOException {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        MediaFile mediaFile = new MediaFile(fileName, fileExtension);
        Path filePath = staticPath.resolve(mediaFile.getFilePath());
        Files.write(filePath, content);
        return repo.save(mediaFile);
    }

    public byte[] read(MediaFile mediaFile) throws IOException {
        return Files.readAllBytes(staticPath.resolve(mediaFile.getFilePath()));
    }

    public void delete(MediaFile mediaFile) throws IOException {
        Path path = staticPath.resolve(mediaFile.getFilePath());
        Files.delete(path);
        repo.delete(mediaFile);
    }

    public boolean exists(MediaFile mediaFile) {
        return Files.exists(staticPath.resolve(mediaFile.getFilePath()));
    }
}
