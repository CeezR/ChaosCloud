package dev.cesar.backend;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String storageFileName = UUID.randomUUID().toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageFileName() {
        return storageFileName;
    }
}
