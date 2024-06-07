package com.socialmedia.socialmedia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();

    @ManyToMany(mappedBy = "savedPost")
    private List<User> usersWhoSaved = new ArrayList<>();

}
