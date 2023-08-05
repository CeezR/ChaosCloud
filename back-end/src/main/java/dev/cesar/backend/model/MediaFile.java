package dev.cesar.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "posted_by")
    private String postedBy = "unknown";

    @Column(name = "created_date", nullable = true, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "storage_file_name", nullable = false)
    private String storageFileName = UUID.randomUUID().toString();
    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    public MediaFile(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }
    public MediaFile(String fileName, String fileExtension, String postedBy) {
        this.fileName = fileName;
        this.postedBy = postedBy;
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
    public String getPostedBy() {
        return postedBy;
    }
    public Date getCreatedDate() {
        return createdDate;
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

    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }
}
