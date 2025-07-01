package com.pavel.folderservice.dtos;

import com.pavel.folderservice.entities.CompositeFileEntity;

public class FolderDto extends AbstractFileDto {

    public FolderDto(CompositeFileEntity file, String parentFolder) {
        super(file.getUuid(), file.getName(), parentFolder, file.getOwner(), file.getCreationDate(), "folder");
    }
}
