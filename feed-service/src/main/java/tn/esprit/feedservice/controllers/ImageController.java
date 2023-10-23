package tn.esprit.feedservice.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Jozef
 */


@Controller
public class ImageController {

    private final String uploadDirectory = "E:/2023_2024_5TWIN5/microservices/inclusify/inclusify/feed-service/src/main/resources/utils/images/"; // Update with your actual directory

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get(uploadDirectory).resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());

            System.out.println(imagePath.toUri().toString());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                // Handle not found or unreadable resource appropriately
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // Handle exceptions appropriately
            return ResponseEntity.status(500).build();
        }
    }
}
