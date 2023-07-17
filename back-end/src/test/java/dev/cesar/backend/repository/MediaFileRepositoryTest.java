package dev.cesar.backend.repository;

import dev.cesar.backend.MediaFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.regex.Pattern;

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

    @Test
    void successfulSaveShouldReturnMediaFile() {
        MediaFile mediaFile = repo.save(new MediaFile());
        assertThat(mediaFile).isNotNull();
    }

    @Test
    void successfulSaveShouldReturnMediaFileWithStorageFileNameAsValidUUID() {
        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        MediaFile mediaFile = repo.save(new MediaFile());

        assertThat(mediaFile).isNotNull();
        assertThat(mediaFile.getStorageFileName()).isNotNull();
        assertThat(mediaFile.getStorageFileName());
        assertThat(UUID_REGEX.matcher(mediaFile.getStorageFileName()).matches()).isTrue();

    }
}