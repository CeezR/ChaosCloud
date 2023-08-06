package dev.cesar.backend.service;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.repository.MediaFileDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MediaFileService {
    private final MediaFileDBRepository repo;
    private final Path staticPath;

    public MediaFileService(MediaFileDBRepository repo, @Value("${static.folder.path}") Path staticPath) {
        this.repo = repo;
        this.staticPath = staticPath;
    }

    public MediaFile store(String fileName, byte[] content) throws IOException {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        MediaFile mediaFile = new MediaFile(fileName, fileExtension, "john", content.length);
        Path filePath = staticPath.resolve(mediaFile.getFilePath());
        Files.write(filePath, content);
        return repo.save(mediaFile);
    }

    public byte[] read(String path) throws IOException {
        return Files.readAllBytes(staticPath.resolve(path));
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
