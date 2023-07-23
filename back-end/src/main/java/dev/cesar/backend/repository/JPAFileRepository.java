package dev.cesar.backend.repository;

import dev.cesar.backend.model.MediaFile;
import org.springframework.data.repository.CrudRepository;

public interface JPAFileRepository extends CrudRepository<MediaFile, Long> {
}
