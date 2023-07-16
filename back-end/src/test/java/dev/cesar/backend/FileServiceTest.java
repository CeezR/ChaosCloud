package dev.cesar.backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    // Path to the static directory
    private static final Path staticPath = Paths.get("src/main/resources/static");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(staticPath);
    }

    @AfterEach
    void tearDown() {
        FileSystemUtils.deleteRecursively(staticPath.toFile());
    }

    @Test
    void testFileStorageAndDeletion() throws IOException {
        // The test file will be named 'testFile.txt'
        Path testFilePath = staticPath.resolve("testFile.txt");

        // Write to the file
        String testFileContent = "Hello, Chaos Cloud!";
        Files.write(testFilePath, testFileContent.getBytes());

        // Assert the file exists
        assertTrue(Files.exists(testFilePath));

        // Read the file
        String readContent = new String(Files.readAllBytes(testFilePath));

        // Assert the file content is as expected
        assertEquals(testFileContent, readContent);

        // Delete the file
        Files.delete(testFilePath);

        // Assert the file no longer exists
        assertFalse(Files.exists(testFilePath));
    }
}