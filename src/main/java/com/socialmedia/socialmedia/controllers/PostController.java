package com.socialmedia.socialmedia.controllers;

import com.socialmedia.socialmedia.entities.Post;
import com.socialmedia.socialmedia.response.ApiResponse;
import com.socialmedia.socialmedia.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        try {
            List<Post> posts = postService.findAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostByIdHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostByIdHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        try {
            String message = postService.deletePost(postId, userId);
            ApiResponse response = new ApiResponse(message, true);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            String errorMessage = "Failed to delete the post: " + ex.getMessage();
            ApiResponse errorResponse = new ApiResponse(errorMessage, false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}