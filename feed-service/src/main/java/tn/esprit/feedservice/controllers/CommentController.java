package tn.esprit.feedservice.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.model.*;
import tn.esprit.feedservice.repositories.*;

import javax.annotation.security.*;

/**
 * @author Jozef
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final ICommentRepository commentRepository;
    private final IPostRepository postRepository;
    private final UserRestFeignClientService userRestFeignClientService;


    public CommentController(ICommentRepository commentRepository, IPostRepository postRepository, UserRestFeignClientService userRestFeignClientService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRestFeignClientService = userRestFeignClientService;
    }

    @PostMapping("/{postId}")
    @PermitAll()
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Post post = postRepository.findById(postId.intValue()).orElse(null);
        if (post == null) {
            return null;
        }

        comment.setPost(post);

        User user = userRestFeignClientService.findById(comment.getUserId());

        if (user == null) {
            // Handle user not found or validation logic as needed
            return null;
        }

        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            // You may want to handle this case differently (e.g., throw an exception)
            return null;
        }

        comment.setComment(updatedComment.getComment());

        // You can add more fields to update as needed

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            commentRepository.deleteById(id);
        }
    }
}

