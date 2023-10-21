package tn.esprit.eventservice.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ImageUploadService {

    @Value("${uploadDir}")
    private String uploadDir;

    public String uploadImage(MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                System.out.println("here file Name: "+ fileName);
                System.out.println("here uploadDir: "+ uploadDir);

                File file = new File(uploadDir + File.separator + fileName);
                imageFile.transferTo(file);

                // Return the image URL
                return "/uploads/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
