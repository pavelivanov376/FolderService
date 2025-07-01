package com.pavel.folderservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity(name = "files")
public class FileEntity extends CompositeFileEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private FolderEntity parentFolder;

    @Column(name = "type")
    private String type;

    public FileEntity() {
        setType("file");
    }

    public FolderEntity getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(FolderEntity parentFolder) {
        this.parentFolder = parentFolder;
    }

}