package tn.esprit.feedservice.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.repositories.*;

import javax.annotation.security.*;
import java.security.*;
import java.util.*;

/**
 * @author Jozef
 */
@RestController
@RequestMapping("/posts")
//@CrossOrigin("http://localhost:5000")
public class PostController {
    private final IPostRepository postRepository;


    private final UserRestFeignClientService userRestFeignClientService;

    @Autowired
    public PostController(IPostRepository postRepository,UserRestFeignClientService userRestFeignClientService) {
        this.postRepository = postRepository;
        this.userRestFeignClientService = userRestFeignClientService;
    }

    @GetMapping("/test")
    @RolesAllowed({"viewer"})
    public String getForTest(Principal principal) {
//        return principal.getName();
        return "A full string for test";
    }

    @GetMapping()
    @RolesAllowed({"admin"})
//    @PermitAll()
    public List<Post> getAllPosts(Principal principal) {

 List<Post> posts = (List<Post>) postRepository.findAll();
 posts.forEach(post -> {
     if(!post.getUserId().isEmpty()){
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

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        // Implement logic to create a new post and save it to the repository
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Integer id, @RequestBody Post updatedPost) {
        // Implement logic to update an existing post in the repository
        // You can use the "id" parameter to identify the post to update
        return postRepository.save(updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        // Implement logic to delete a post by ID from the repository
        postRepository.deleteById(id);
    }
}
