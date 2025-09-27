package com.pavel.folderservice.dtos;

public record CreateFileDto(String name, String uuid, String parentFolderId) {
}
