package com.pavel.folderservice.controllers;

import com.pavel.folderservice.dtos.CreateFileDto;
import com.pavel.folderservice.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/files")
@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/api/file/create")
    public ResponseEntity<String> createFile(@RequestBody CreateFileDto file/*, Principal principal*/) {

        return ResponseEntity.ok(fileService.create(file));
    }

}