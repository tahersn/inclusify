package tn.esprit.feedservice.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.model.*;
import tn.esprit.feedservice.repositories.*;

import java.util.*;

/**
 * @author Jozef
 */
@RestController
@RequestMapping("/reacts")
public class ReactController {

    private final IReactRepository reactRepository;
    private final IPostRepository postRepository;
    private final UserRestFeignClientService userRestFeignClientService;


    public ReactController(IReactRepository reactRepository, IPostRepository postRepository, UserRestFeignClientService userRestFeignClientService) {
        this.reactRepository = reactRepository;
        this.postRepository = postRepository;
        this.userRestFeignClientService = userRestFeignClientService;
    }

    @PostMapping("/{postId}")
    public React createReact(@PathVariable Long postId, @RequestBody React react) {
        Post post = postRepository.findById(postId.intValue()).orElse(null);
        if (post == null) {
            return null;
        }

        // Add the comment to the post's collection of comments
        List<React> reacts = post.getReacts();
        reacts.add(react);

        // Update the post's comments collection
        post.setReacts(reacts);

        // Set the post for the comment
        react.setPost(post);

        if (react.getUserId() == null) {
            return null;
        }
        User user = userRestFeignClientService.findById(react.getUserId());
        if (user == null) {
            // Handle user not found or validation logic as needed
            return null;
        }
        react.setUser(user);

        // Save the updated post with the new comment
        postRepository.save(post);

        return reactRepository.save(react);
    }

    @GetMapping("/{id}")
    public React getReactById(@PathVariable Long id) {
        return reactRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public React updateReact(@PathVariable Long id, @RequestBody React updatedReact) {
        React react = reactRepository.findById(id).orElse(null);
        if (react == null) {
            // Handle this case differently (e.g., throw an exception or return null)
            return null;
        }

        react.setReactType(updatedReact.getReactType());

        // You can add more fields to update as needed

        return reactRepository.save(react);
    }

    @DeleteMapping("/{id}")
    public void deleteReact(@PathVariable Long id) {
        React react = reactRepository.findById(id).orElse(null);
        if (react != null) {
            reactRepository.deleteById(id);
        }
    }
}
