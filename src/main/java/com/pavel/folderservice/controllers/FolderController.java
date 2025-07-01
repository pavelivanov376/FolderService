package com.pavel.folderservice.controllers;

import com.pavel.folderservice.dtos.AbstractFileDto;
import com.pavel.folderservice.dtos.CreateFolderDto;
import com.pavel.folderservice.services.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/api/files")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/api/folder/{uuid}")
    public ResponseEntity<Collection<AbstractFileDto>> getFolderContent(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(folderService.listByUuid(uuid));
    }

    @PostMapping("/api/folder/create")
    public ResponseEntity<String> createFolder(@RequestBody CreateFolderDto folder/*, Principal principal*/) {

        return ResponseEntity.ok(folderService.create(folder));
    }

    //TODO: For testing purposes only, remove later
    @PostMapping("/api/folder/create/{name}")
    public ResponseEntity<String> createFolder(@PathVariable("name") String name) {
        CreateFolderDto folder = new CreateFolderDto(name, "10000000-0000-0000-0000-000000000001");

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
