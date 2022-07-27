package com.springter.business.service;


import com.springter.business.repository.model.PostDAO;
import com.springter.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();

    List<Post> findByUserId(Long userId);

    PostDAO postTweet(Post post);

    Optional<Post> findByPostId(Long id);

    void deletePost(Long id);
}
