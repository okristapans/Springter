package com.springter.business.service;


import com.springter.business.repository.model.PostDAO;
import com.springter.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    List<PostDAO> getAllPosts();

    List<Post> findByUserId(Long userId);

    PostDAO postTweet(Post post);

    Optional<PostDAO> findByPostId(Long id);
}
