package dev.cesar.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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