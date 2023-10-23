package tn.esprit.feedservice.services.utils;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import tn.esprit.feedservice.Configuration.*;
import tn.esprit.feedservice.services.Exceptions.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

/**
 * @author Jozef
 */
@Service
public class ImageService {

    private static final String IMAGE_UPLOAD_PATH = "/utils/images/"; // Define the folder where images will be stored

    @Autowired
    public void FileStorageService(FileStorageProperties fileStorageProperties) {
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String saveImageToServer(MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                // Generate a unique filename for the image
                String uniqueFileName = generateUniqueFileName(image.getOriginalFilename());

                // Define the full path to save the image
                String imagePath = IMAGE_UPLOAD_PATH + uniqueFileName;

                // Create a File object for the image
                File imageFile = new File(imagePath);

                // Ensure the parent directory exists
                imageFile.getParentFile().mkdirs();

                // Save the image to the server
                image.transferTo(imageFile);

                // Return the URL of the saved image
                return "/images/" + uniqueFileName; // Replace with your actual URL path
            } catch (IOException e) {
                // Handle the exception (e.g., log the error or throw a custom exception)
                e.printStackTrace();
            }
        }
        return null;
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = StringUtils.getFilenameExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;
        return uniqueFileName;
    }
}
