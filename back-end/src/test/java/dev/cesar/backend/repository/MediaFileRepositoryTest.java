package dev.cesar.backend.repository;

import dev.cesar.backend.MediaFile;
import dev.cesar.backend.repository.MediaFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MediaFileRepositoryTest {

    @Autowired
    MediaFileRepository repo;

    @Test
    void saveShouldIncreaseTotalFilesInDBBy1() {
        int count = repo.findAll().size();

        repo.save(new MediaFile());

        assertThat(repo.findAll().size()).isEqualTo(count + 1);
    }
}