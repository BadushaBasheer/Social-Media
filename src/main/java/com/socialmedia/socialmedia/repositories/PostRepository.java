package com.socialmedia.socialmedia.repositories;

import com.socialmedia.socialmedia.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.id =:userId")
    List<Post> findPostByUserId(@Param("userId") Integer userId);

}