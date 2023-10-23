package tn.esprit.feedservice.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.feedservice.entities.*;
import tn.esprit.feedservice.feign.*;
import tn.esprit.feedservice.model.*;
import tn.esprit.feedservice.repositories.*;

import javax.annotation.security.*;
import java.util.*;

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

        // Add the comment to the post's collection of comments
        List<Comment> comments = post.getComments();
        comments.add(comment);

        // Update the post's comments collection
        post.setComments(comments);

        // Set the post for the comment
        comment.setPost(post);

        if (comment.getUserId() == null) {
            return null;
        }

        User user = userRestFeignClientService.findById(comment.getUserId());

        if (user == null) {
            // Handle user not found or validation logic as needed
            return null;
        }

        comment.setUser(user);

        // Save the updated post with the new comment
        postRepository.save(post);

        // Save and return the comment
        return commentRepository.save(comment);
    }

    @GetMapping("byId/{id}")
    public Comment getCommentById(@PathVariable Long id) {

        return commentRepository.findById(id).orElse(null);
//        if (c != null) {
//            Post p = c.getPost();
//            System.out.println(p);
//
//        }
//        return c;
    }

    @GetMapping("/")
    public List<Comment> getAllComment() {
        return (List<Comment>) commentRepository.findAll();
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

