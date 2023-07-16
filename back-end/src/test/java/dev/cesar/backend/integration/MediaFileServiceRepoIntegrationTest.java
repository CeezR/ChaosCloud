package dev.cesar.backend.integration;

import dev.cesar.backend.repository.MediaFileRepository;
import dev.cesar.backend.service.MediaFileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MediaFileServiceRepoIntegrationTest {
    @Autowired
    private MediaFileService service;
    @Autowired
    private MediaFileRepository repo;
    private static final String TEST_FILE_NAME = "testFile.txt";
    private static final String TEST_FILE_CONTENT = "Hello, Chaos Cloud!";
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
    void testThatOnFileSaveNewMediaFileEntityIsAddedToDB() throws IOException {
        int count = repo.findAll().size();

        // Store the file
        service.store(TEST_FILE_NAME, TEST_FILE_CONTENT.getBytes());
        String readContent = new String(service.read(TEST_FILE_NAME));

        // Assert the file exists
        assertTrue(service.exists(TEST_FILE_NAME));
        assertEquals(TEST_FILE_CONTENT, readContent);
        assertThat(repo.findAll().size()).isEqualTo(count + 1);
    }
}
