package com.pavel.folderservice.controllers;

import com.pavel.folderservice.dtos.FolderDto;

import com.pavel.folderservice.services.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/api/folder/{uuid}")
    public ResponseEntity<Collection<FolderDto>> getFolderContent(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(folderService.listByUuid(uuid));
    }

    @PostMapping("/api/folder/create")
    public ResponseEntity<String> createFolder(FolderDto folder/*, Principal principal*/) {
        folder.setOwner("pavel");

        return ResponseEntity.ok(folderService.create(folder));
    }

    @PostMapping("/api/folder/create/{name}")
    public ResponseEntity<String> createFolder(@PathVariable("name") String name) {
        FolderDto folder = new FolderDto(name);

        return ResponseEntity.ok(folderService.create(folder));
    }

    @DeleteMapping("/api/folder/{uuid}")
    public ResponseEntity<String> deleteFolder(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(folderService.delete(uuid));
    }
//    @GetMapping("/api/home/id")
//    public ResponseEntity<String> getHomeFolderContent(Principal principal) {
//        String homeFolderID = userRepository.findByName(principal.getName()).get().getHomeFolderUuid();
//        return ResponseEntity.ok(homeFolderID);
//    }
//    @PostMapping("/api/folder/create")
//    public ResponseEntity<String> createFolder(FolderDto folder, Principal principal) {
//        folder.setOwner(principal.getName());
//        folderService.create(folder);
//
//        return ResponseEntity.ok("Folder created");
//    }
//    @PostMapping("/api/folder/share")
//    public ResponseEntity<String> shareFile(ShareDTO shareDTO) throws IOException {
//        return ResponseEntity.ok(folderService.share(shareDTO));
//    }
}
