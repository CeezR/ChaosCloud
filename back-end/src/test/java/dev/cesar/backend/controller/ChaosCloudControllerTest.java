package dev.cesar.backend.controller;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.model.MediaFileRequestDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ChaosCloudControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    RestTemplate restTemplate;

    private static final Path TEST_FILES_PATH = Paths.get("src/test/resources/testFiles");
    private static final Path STATIC_PATH = Paths.get("src/test/resources/static");

    @BeforeAll
    static void setUp() throws IOException {
        Files.createDirectories(STATIC_PATH);
    }

    @AfterAll
    static void tearDown() {
        FileSystemUtils.deleteRecursively(STATIC_PATH.toFile());
    }

    @Test
    void testPostMapping() throws Exception {
        String uri = "http://localhost:%s/api/files".formatted(port);

        Path sourceFilePath = TEST_FILES_PATH.resolve("test.pdf");
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFileRequestDTO requestDTO = new MediaFileRequestDTO("test.pdf", content);

        ResponseEntity<MediaFile> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(requestDTO), MediaFile.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFileName()).isEqualTo("test.pdf");
    }

    @Test
    void testGetAllFilesMapping() {
        String uri = "http://localhost:%s/api/files".formatted(port);

        Path sourceFilePath = TEST_FILES_PATH.resolve("test.pdf");

        ResponseEntity<List<MediaFile>> response = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<MediaFile>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
    }

    @Test
    void testDownloadFilesMapping() throws IOException {
        String uri = "http://localhost:%s/api/files".formatted(port);

        Path sourceFilePath = TEST_FILES_PATH.resolve("test.pdf");
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFileRequestDTO requestDTO = new MediaFileRequestDTO("test.pdf", content);
        ResponseEntity<MediaFile> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(requestDTO), MediaFile.class);

        String downloadUri = "http://localhost:%s/api/files/%s".formatted(port, response.getBody().getId());

        ResponseEntity<MediaFileRequestDTO> downloadResponse = restTemplate.exchange(downloadUri, HttpMethod.GET, HttpEntity.EMPTY, MediaFileRequestDTO.class);

        assertThat(downloadResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(downloadResponse.hasBody()).isNotNull();
        assertThat(downloadResponse.getBody().fileName()).isEqualTo("test.pdf");
    }
}