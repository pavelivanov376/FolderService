package com.pavel.folderservice.services;

import com.pavel.folderservice.dtos.CreateFileDto;
import com.pavel.folderservice.dtos.FileDto;
import com.pavel.folderservice.entities.FileEntity;
import com.pavel.folderservice.entities.FolderEntity;
import com.pavel.folderservice.repositories.FileRepository;
import com.pavel.folderservice.repositories.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {
    private FileRepository fileRepository = null;
    private FolderRepository folderRepository = null;

    public FileService(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    public String create(CreateFileDto fileDto) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileDto.name());

        FolderEntity parentFolder = folderRepository.findByUuid(fileDto.parentFolderId());
        fileEntity.setParentFolder(parentFolder);
        fileEntity.setUuid(UUID.randomUUID().toString());
        FileEntity saved = fileRepository.save(fileEntity);

        return saved.getUuid();
    }

    public Collection<FileDto> findAllContentByParentFolder(String parenFolderUuidd) {
        Collection<FileEntity> content = new ArrayList<>();

        content.addAll(fileRepository.findAllByParentFolderUuid(parenFolderUuidd));

        return content.stream().map(e -> new FileDto(e, parenFolderUuidd)).collect(Collectors.toList());
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
//    public Collection<FolderDto> listByUuid(String uuid) {
//        Collection<FolderEntity> content = new LinkedHashSet<>();
//
//        FolderEntity folderEntity = folderRepository.findByUuid(uuid);
//        content.addAll(folderEntity.getContainedFolders());
//
//        List<FolderDto> result = new ArrayList<>();
//        if (!isMainRootDirectory(folderEntity)) {
//            FolderDto parent = new FolderDto(folderEntity.getParentFolder(), uuid).setName("/..");
//            result.add(parent);
//        }
//        result.addAll(content.stream().map(e -> new FolderDto(e, uuid)).collect(Collectors.toList()));
//        //TODO: add files here as well
//        result.sort(Comparator.comparing(FolderDto::getName));
//        return result;
//    }
//
//    public FolderEntity findByUuid(String uuid) {
//        return folderRepository.findByUuid(uuid);
//    }
//
//    private boolean isMainRootDirectory(FolderEntity folderEntity) {
//        return folderEntity.getParentFolder().getUuid().equals("10000000-0000-0000-0000-000000000001");
//    }
//
//    public String delete(String uuid) {
//        folderRepository.deleteByUuid(uuid);
//        return "Folder deleted";
//    }
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
