package tn.esprit.feedservice.services.utils;


import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;
import tn.esprit.feedservice.Configuration.*;
import tn.esprit.feedservice.services.Exceptions.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
/**
 * @author Jozef
 */
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains(".."))
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            System.out.println(targetLocation.toString());
            System.out.println(targetLocation.toAbsolutePath());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toAbsolutePath().toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
