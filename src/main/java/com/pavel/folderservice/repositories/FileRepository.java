package com.pavel.folderservice.repositories;

import com.pavel.folderservice.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Collection<FileEntity> findAllByParentFolderUuid(String parenFolderId);
}
