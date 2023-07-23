package dev.cesar.backend.controller;

import dev.cesar.backend.model.MediaFile;
import dev.cesar.backend.model.MediaFileRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ChaosCloudControllerTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    RestTemplate restTemplate;

    private static final Path TEST_FILES_PATH = Paths.get("src/test/resources/testFiles");

    @Test
    void testPostMapping() throws Exception {
        String uri = "http://localhost:%s/api/files".formatted(port);

        Path sourceFilePath = TEST_FILES_PATH.resolve("test.pdf");
        byte[] content = Files.readAllBytes(sourceFilePath);
        MediaFileRequestDTO requestDTO = new MediaFileRequestDTO("test.pdf", content);

        ResponseEntity<MediaFile> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(requestDTO), MediaFile.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}