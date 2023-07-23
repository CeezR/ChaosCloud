package dev.cesar.backend.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "storage_file_name", nullable = false)
    private String storageFileName = UUID.randomUUID().toString();
    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    public MediaFile(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public MediaFile() {

    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
    public String getStorageFileName() {
        return storageFileName;
    }
    public String getFilePath() {
        return storageFileName + fileExtension;
    }
    public String getFileExtension() {
        return fileExtension;
    }
}
