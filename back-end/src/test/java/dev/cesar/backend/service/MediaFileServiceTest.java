package dev.cesar.backend.service;

import dev.cesar.backend.model.MediaFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MediaFileServiceTest {

    @Autowired
    private MediaFileService mediaFileService;
    private static final Path STATIC_PATH = Paths.get("src/test/resources/static");
    private static final Path TEST_FILES_PATH = Paths.get("src/test/resources/testFiles");

    @BeforeEach
    void setUp() throws IOException {
        // Ensure the static directory exists
        Files.createDirectories(STATIC_PATH);
    }

    @AfterEach
    void tearDown() {
        // Clean up the static directory
        FileSystemUtils.deleteRecursively(STATIC_PATH.toFile());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png", "test.pdf", "test.docx"})
    void testFileStorage(String fileName) throws IOException {
        Path sourceFilePath = TEST_FILES_PATH.resolve(fileName);
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFile mediaFile = mediaFileService.store(fileName, content);

        assertTrue(mediaFileService.exists(mediaFile));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png", "test.pdf", "test.docx"})
    void testFileDeletion(String fileName) throws IOException {
        Path sourceFilePath = TEST_FILES_PATH.resolve(fileName);
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFile mediaFile = mediaFileService.store(fileName, content);
        mediaFileService.delete(mediaFile);

        assertFalse(mediaFileService.exists(mediaFile));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png", "test.pdf", "test.docx"})
    void testFileContent(String fileName) throws IOException {
        Path sourceFilePath = TEST_FILES_PATH.resolve(fileName);
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFile mediaFile = mediaFileService.store(fileName, content);
        byte[] readContent = mediaFileService.read(mediaFile);

        assertArrayEquals(content, readContent);
    }
}