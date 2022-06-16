package com.springter.business.service;


import com.springter.business.repository.model.PostDAO;
import com.springter.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> getAllPosts();

    List<Post> findByUserId(Long userId);

    PostDAO postTweet(Post post);
}
