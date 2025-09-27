package com.pavel.folderservice.controllers;

import com.pavel.folderservice.dtos.CreateFileDto;
import com.pavel.folderservice.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/file")
@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/link/folder")
    public ResponseEntity<String> createFile(@RequestBody CreateFileDto file/*, Principal principal*/) {

        return ResponseEntity.ok(fileService.create(file));
    }

}