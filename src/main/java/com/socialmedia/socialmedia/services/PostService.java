package com.socialmedia.socialmedia.services;

import com.socialmedia.socialmedia.entities.Post;

import java.util.List;

public interface PostService {

    List<Post> findAllPosts();
    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    Post createNewPost(Post post, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;

    Post commentPost(Integer postId, Integer userId) throws Exception;

    Post savedPost(Integer postId, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;
}
