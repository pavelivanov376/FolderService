package com.pavel.folderservice.dtos;

import com.pavel.folderservice.entities.CompositeFileEntity;

import java.time.LocalDateTime;

public class AbstractFileDto {
    private String uuid;
    private String name;
    private String parentFolderId;
    private String owner;
    private LocalDateTime creationDate;
    private String type;

    public AbstractFileDto() {
    }

    public AbstractFileDto(CompositeFileEntity file, String parentFolder) {
        this(file.getUuid(), file.getName(), parentFolder, file.getOwner(), file.getCreationDate(), "folder");

    }

    public AbstractFileDto(String uuid, String name, String parentFolderId, String owner, LocalDateTime creationDate, String type) {
        this.uuid = uuid;
        this.name = name;
        this.parentFolderId = parentFolderId;
        this.owner = owner;
        this.creationDate = creationDate;
    }

    public String getUuid() {
        return uuid;
    }

    public AbstractFileDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractFileDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public AbstractFileDto setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public AbstractFileDto setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public AbstractFileDto setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getType() {
        return type;
    }

    public AbstractFileDto setType(String type) {
        this.type = type;
        return this;
    }
}
