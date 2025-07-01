package com.pavel.folderservice.dtos;

import com.pavel.folderservice.entities.FileEntity;

public class FileDto extends AbstractFileDto {

    public FileDto(FileEntity file, String parentFolder) {
        super(file.getUuid(), file.getName(), parentFolder, file.getOwner(), file.getCreationDate(), "file");
    }
}
