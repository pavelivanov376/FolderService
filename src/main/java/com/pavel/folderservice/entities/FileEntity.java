package com.pavel.folderservice.entities;

import jakarta.persistence.*;

@Entity(name = "files")
public class FileEntity extends CompositeFileEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private FolderEntity parentFolder;

    @Column(name = "type")
    private String type;

    public FolderEntity getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(FolderEntity parentFolder) {
        this.parentFolder = parentFolder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}