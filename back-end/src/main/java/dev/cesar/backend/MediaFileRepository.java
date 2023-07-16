package dev.cesar.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MediaFileRepository {
    private final JPAFileRepository repo;

    @Autowired
    public MediaFileRepository(JPAFileRepository repo) {
        this.repo = repo;
    }

    public MediaFile save(MediaFile mediaFile) {
        return repo.save(mediaFile);
    }

    public List<MediaFile> findAll() {
        return Streamable.of(repo.findAll()).toList();
    }
}
