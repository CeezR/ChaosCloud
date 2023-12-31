package dev.cesar.backend.repository;

import dev.cesar.backend.model.MediaFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MediaFileDBRepository {
    private final JPAFileRepository repo;

    @Autowired
    public MediaFileDBRepository(JPAFileRepository repo) {
        this.repo = repo;
    }

    public MediaFile save(MediaFile mediaFile) {
        return repo.save(mediaFile);
    }

    public List<MediaFile> findAll() {
        return Streamable.of(repo.findAll()).toList();
    }

    public MediaFile findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(MediaFile mediaFile) {
        repo.delete(mediaFile);
    }
}
