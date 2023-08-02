package dev.cesar.backend.integration;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.repository.MediaFileDBRepository;
import dev.cesar.backend.service.MediaFileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MediaFileServiceRepoIntegrationTest {
    @Autowired
    private MediaFileService service;
    @Autowired
    private MediaFileDBRepository repo;
    private static final String TEST_FILE_NAME = "testFile.txt";
    private static final String TEST_FILE_CONTENT = "Hello, Chaos Cloud!";
    private static final Path STATIC_PATH = Paths.get("src/test/resources/static");
    private static final Path TEST_FILES_PATH = Paths.get("src/test/resources/testFiles");


    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(STATIC_PATH);
    }

    @AfterEach
    void tearDown() {
        FileSystemUtils.deleteRecursively(STATIC_PATH.toFile());
    }

    @Test
    void testThatOnFileSaveNewMediaFileEntityIsAddedToDB() throws IOException {
        int count = repo.findAll().size();

        MediaFile mediaFile = service.store(TEST_FILE_NAME, TEST_FILE_CONTENT.getBytes());

        assertTrue(service.exists(mediaFile));
        assertThat(repo.findAll().size()).isEqualTo(count + 1);
    }

    @Test
    void testThatSavedFilePathIncludeUUID() throws IOException {
        Pattern UUID_REGEX =
                Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        // Store the file
        MediaFile mediaFile = service.store(TEST_FILE_NAME, TEST_FILE_CONTENT.getBytes());

        Matcher matcher = UUID_REGEX.matcher(mediaFile.getStorageFileName());
        // Check if a UUID is present in the path string.
        assertTrue(matcher.find());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.jpg", "test.png", "test.pdf", "test.docx"})
    void testThatSavedFilePathIncludeCorrectType(String fileName) throws IOException {
        Path sourceFilePath = TEST_FILES_PATH.resolve(fileName);
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFile mediaFile = service.store(fileName, content);

        assertTrue(mediaFile.getFilePath().endsWith(fileName.substring(fileName.lastIndexOf("."))));
    }


    @Test
    void testThatOnFileDeleteNewMediaFileEntityIsRemovedFromDB() throws IOException {
        MediaFile mediaFile = service.store(TEST_FILE_NAME, TEST_FILE_CONTENT.getBytes());
        int count = repo.findAll().size();
        service.delete(mediaFile);

        assertFalse(service.exists(mediaFile));
        assertThat(repo.findAll().size()).isEqualTo(count - 1);
    }
}
