package com.pavel.folderservice.config;

import com.pavel.folderservice.entities.FolderEntity;
import com.pavel.folderservice.repositories.FolderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Initializes required data on application startup.
 * Creates the root folder that serves as the parent for all user folders.
 * This will need to happen at new user registration
 * Current setup is for testing purposes
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    public static final String ROOT_FOLDER_UUID = "10000000-0000-0000-0000-000000000001";
    public static final String ROOT_FOLDER_NAME = "root";

    public static final String ADMIN_FOLDER_UUID = "10000000-0000-0000-0000-000000000002";
    public static final String ADMIN_FOLDER_NAME = "admin";
    public static final String ADMIN_USER = "admin";

    private final FolderRepository folderRepository;

    public DataInitializer(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        FolderEntity rootFolder = createRootFolderIfNotExists();
        createAdminFolderIfNotExists(rootFolder);
    }

    private FolderEntity createRootFolderIfNotExists() {
        FolderEntity existingRoot = folderRepository.findByUuid(ROOT_FOLDER_UUID);

        if (existingRoot != null) {
            logger.info("Root folder already exists with UUID: {}", ROOT_FOLDER_UUID);
            return existingRoot;
        }

        FolderEntity rootFolder = new FolderEntity();
        rootFolder.setUuid(ROOT_FOLDER_UUID);
        rootFolder.setName(ROOT_FOLDER_NAME);
        rootFolder.setOwner("system");
        rootFolder.setCreationDate(LocalDateTime.now());
        rootFolder.setParentFolder(null); // Root has no parent

        FolderEntity saved = folderRepository.save(rootFolder);
        logger.info("Created root folder with UUID: {}", ROOT_FOLDER_UUID);
        return saved;
    }

    private void createAdminFolderIfNotExists(FolderEntity rootFolder) {
        FolderEntity existingAdminFolder = folderRepository.findByUuid(ADMIN_FOLDER_UUID);

        if (existingAdminFolder != null) {
            logger.info("Admin folder already exists with UUID: {}", ADMIN_FOLDER_UUID);
            return;
        }

        FolderEntity adminFolder = new FolderEntity();
        adminFolder.setUuid(ADMIN_FOLDER_UUID);
        adminFolder.setName(ADMIN_FOLDER_NAME);
        adminFolder.setOwner(ADMIN_USER);
        adminFolder.setCreationDate(LocalDateTime.now());
        adminFolder.setParentFolder(rootFolder);

        folderRepository.save(adminFolder);
        logger.info("Created admin folder with UUID: {} under root folder", ADMIN_FOLDER_UUID);
    }
}
