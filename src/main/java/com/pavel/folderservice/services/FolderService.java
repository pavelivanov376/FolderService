package com.pavel.folderservice.services;

import com.pavel.folderservice.dtos.FolderDto;
import com.pavel.folderservice.entities.FolderEntity;
import com.pavel.folderservice.repositories.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FolderService {
    private final FolderRepository folderRepository;
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public String create(FolderDto folderDto) {
        FolderEntity folderEntity = new FolderEntity();

        folderEntity.setName("/" + folderDto.getName());
        folderEntity.setOwner(folderDto.getOwner());

        FolderEntity parentFolder = folderRepository.findByUuid(folderDto.getParentFolderId());
//        folderEntity.setParentFolder("parentFolder");
        folderEntity.setUuid(UUID.randomUUID().toString());
        folderEntity.setCreationDate(LocalDateTime.now());

        FolderEntity savedFolder = folderRepository.save(folderEntity);
        return savedFolder.getUuid();
    }

    public Collection<FolderDto> findAllContentByParentFolder(String parenFolderUuidd) {
        Collection<FolderEntity> content = new ArrayList<>();

        content.addAll(folderRepository.findAllByParentFolderUuid(parenFolderUuidd));

        return content.stream().map(e -> new FolderDto(e, parenFolderUuidd)).collect(Collectors.toList());
    }

//    public String createHomeFolderForUser(UserDTO userDTO) {
//        FolderDto folderDto = new FolderDto()
//                .setName(userDTO.getName())
//                .setParentFolderId("10000000-0000-0000-0000-000000000001")
//                .setOwner(userDTO.getName());
//
//        return create(folderDto);
//    }
//    public String createSharedFolderForUser(UserDTO userDTO, String homeFolderId) {
//        FolderDto folderDto = new FolderDto()
//                .setName("shared")
//                .setParentFolderId(homeFolderId)
//                .setOwner(userDTO.getName());
//
//        return create(folderDto);
//    }
//
    public Collection<FolderDto> listByUuid(String uuid) {
        Collection<FolderEntity> content = new LinkedHashSet<>();

        FolderEntity folderEntity = folderRepository.findByUuid(uuid);
//        content.addAll(folderEntity.getContainedFolders());

        List<FolderDto> result = new ArrayList<>();
//        if (!isMainRootDirectory(folderEntity)) {
//            BaseEntityDTO parent = new BaseEntityDTO(folderEntity.getParentFolder(), uuid).setName("/..");
//            result.add(parent);
//        }
        result.addAll(content.stream().map(e -> new FolderDto(e, uuid)).collect(Collectors.toList()));
        result.sort(Comparator.comparing(FolderDto::getName));
        return result;
    }

    public FolderEntity findByUuid(String uuid) {
        return folderRepository.findByUuid(uuid);
    }

//    private boolean isMainRootDirectory(FolderEntity folderEntity) {
//        return folderEntity.getParentFolder().getUuid().equals("10000000-0000-0000-0000-000000000001");
//    }
//
    public String delete(String uuid) {
        folderRepository.deleteByUuid(uuid);
        return "Folder deleted";
    }
//
//    public String share(ShareDTO dto) {
//        Optional<UserEntity> shareUser = userRepository.findByName(dto.getShareWith());
//        if (shareUser.isPresent()) {
//            FolderEntity folderEntity = folderFactory.createSharedFolder(dto, shareUser.get());
//
//            folderRepository.save(folderEntity);
//            return "Folder " + dto.getUuid() + " shared with " + dto.getShareWith();
//        }
//
//        return "User " + dto.getShareWith() + "not found!";
//
//    }
}
