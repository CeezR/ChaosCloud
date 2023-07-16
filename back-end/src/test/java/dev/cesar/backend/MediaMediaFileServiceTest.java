package dev.cesar.backend;

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
class MediaMediaFileServiceTest {

    @Autowired
    private MediaFileService mediaFileService;

    private static final Path staticPath = Paths.get("src/main/resources/static");
    private static final Path testFilesPath = Paths.get("src/test/resources/testFiles");

    @BeforeEach
    void setUp() throws IOException {
        // Ensure the static directory exists
        Files.createDirectories(staticPath);
    }

    @AfterEach
    void tearDown() {
        // Clean up the static directory
        FileSystemUtils.deleteRecursively(staticPath.toFile());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png", "test.pdf", "test.docx"})
    void testFileStorageAndDeletion(String fileName) throws IOException {
        // Read the test file
        Path sourceFilePath = testFilesPath.resolve(fileName);
        byte[] content = Files.readAllBytes(sourceFilePath);

        // Store the file
        mediaFileService.store(fileName, content);

        // Assert the file exists
        assertTrue(mediaFileService.exists(fileName));

        // Read the file
        byte[] readContent = mediaFileService.read(fileName);

        // Assert the file content is as expected
        assertArrayEquals(content, readContent);

        // Delete the file
        mediaFileService.delete(fileName);

        // Assert the file no longer exists
        assertFalse(mediaFileService.exists(fileName));
    }
}