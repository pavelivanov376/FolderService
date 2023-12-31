package com.pavel.folderservice.repositories;

import com.pavel.folderservice.entities.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    FolderEntity findByName(String name);

    Collection<FolderEntity> findAllByParentFolderUuid(String parenFolderId);

    FolderEntity findByUuid(String uuid);
    @Transactional
    void deleteByUuid(String uuid);
}
