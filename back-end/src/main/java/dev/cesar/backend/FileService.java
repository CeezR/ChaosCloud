package dev.cesar.backend;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final Path staticPath = Paths.get("src/main/resources/static");

    public Path store(String fileName, String content) throws IOException {
        Path filePath = staticPath.resolve(fileName);
        Files.write(filePath, content.getBytes());
        return filePath;
    }

    public String read(String fileName) throws IOException {
        Path filePath = staticPath.resolve(fileName);
        return new String(Files.readAllBytes(filePath));
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
