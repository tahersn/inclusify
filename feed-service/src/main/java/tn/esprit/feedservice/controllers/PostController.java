package tn.esprit.feedservice.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.model.*;
import tn.esprit.feedservice.repositories.*;
import tn.esprit.feedservice.services.utils.*;

import javax.annotation.security.*;
import java.io.*;
import java.security.*;
import java.util.*;

/**
 * @author Jozef
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    private final IPostRepository postRepository;

    private final ImageService imageService;
    private final UserRestFeignClientService userRestFeignClientService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    public PostController(IPostRepository postRepository, ImageService imageService, UserRestFeignClientService userRestFeignClientService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
        this.userRestFeignClientService = userRestFeignClientService;
    }

    @GetMapping("/test")
    @RolesAllowed({"viewer"})
    public String getForTest(Principal principal) {
//        return principal.getName();
        return "A full string for test";
    }

    @Transactional
    @GetMapping(value = {"/byUser/{userId}"})
    public List<Post> getAllPosts(@PathVariable String userId,Principal principal) {
        if (userRestFeignClientService.findById(userId)!=null){
        List<Post> posts = (List<Post>) postRepository.getPostsByUser(userId);

        // Fetch comments for each post
        posts.forEach(post -> {
            List<Comment> comments = post.getComments();
            comments.size(); // Trigger lazy loading

            List<React> reacts = post.getReacts();
            reacts.size(); // Trigger lazy loading
            if (!(post.getUserId() == null)) {
                post.setUser(userRestFeignClientService.findById(post.getUserId()));
            }
        });

        return posts;}
        return new ArrayList<>();
    }

    @Transactional
    @GetMapping()
    @RolesAllowed({"admin"})
    public List<Post> getAllPosts(Principal principal) {
        List<Post> posts = (List<Post>) postRepository.findAll();

        // Fetch comments for each post
        posts.forEach(post -> {
            List<Comment> comments = post.getComments();
            comments.size(); // Trigger lazy loading

            List<React> reacts = post.getReacts();
            reacts.size(); // Trigger lazy loading
            if (!(post.getUserId() == null)) {
                post.setUser(userRestFeignClientService.findById(post.getUserId()));
            }
        });

        return posts;
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        // Implement logic to retrieve a post by ID from the repository
        return postRepository.findById(id).orElse(null);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PermitAll()
    public Post createPost(@RequestParam("images") MultipartFile[] images, @RequestParam("description") String description, @RequestParam("userId") String userId) {
        Post post = new Post();
        post.setDescription(description);
        post.setUserId(userId);

        // Fetch user information from the user service
        User user = userRestFeignClientService.findById(userId);
        if (user != null) {
            post.setUser(user);
        } else {
            // Handle user not found or validation logic as needed
            return null;
        }

        // Save the images and store their URLs in the post
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            // Save the image to the server
            String imageUrl = saveImageToServer(image);
//            String imageUrl = fileStorageService.storeFile(image);
            if (imageUrl != null) {
                imageUrls.add("http://localhost:9999/feed-service/images/"+imageUrl);
            }
        }

        post.setImages(imageUrls);

        // Save the post to the repository
        return postRepository.save(post);
    }

    private String saveImageToServer(MultipartFile image) {
        try {
            // Define the directory where you want to store images
            String uploadDirectory = "E:/2023_2024_5TWIN5/microservices/inclusify/inclusify/feed-service/src/main/resources/utils/images/";
//            String uploadDirectory = "classpath:/utils/images/";
            // Generate a unique filename (you may use UUID or any other approach)
            String uniqueFileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();

            // Construct the full path to save the file
            String filePath = uploadDirectory + uniqueFileName;

            // Save the file
            image.transferTo(new File(filePath));

            // Return the URL of the saved image
            return uniqueFileName; // You can customize the URL as needed
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately
        }
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post updatedPost) {
        // Implement logic to update an existing post in the repository
        // You can use the "id" parameter to identify the post to update
        return postRepository.save(updatedPost);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("superAdmin")
    public void deletePost(@PathVariable Integer id) {
        // Implement logic to delete a post by ID from the repository
        postRepository.deleteById(id);
    }
}
