package com.pavel.folderservice.dtos;

import com.pavel.folderservice.entities.FolderEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class FolderDto {
    private String uuid;
    private String name;
    private String parentFolderId;
    private String owner;
    private LocalDateTime creationDate;

    public FolderDto(FolderEntity file, String parentFolder) {
        this.name = file.getName();
        this.parentFolderId = parentFolder;
        this.owner = file.getOwner();
        this.creationDate = file.getCreationDate();
        this.uuid = file.getUuid();
    }

    public FolderDto(String name) {
        this.name = name;
        this.parentFolderId = "da1c835a-5a5c-49b2-a88e-8f5647950954";
        this.owner = "unknown";
        this.creationDate = LocalDateTime.now();
        this.uuid = UUID.randomUUID().toString();
    }

    public String getOwner() {
        return owner;
    }

    public FolderDto setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getName() {
        return name;
    }

    public FolderDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public FolderDto setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public FolderDto setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public FolderDto setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
