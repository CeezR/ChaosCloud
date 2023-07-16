package dev.cesar.backend;

import org.springframework.data.repository.CrudRepository;

public interface JPAFileRepository extends CrudRepository<MediaFile, Long> {
}
