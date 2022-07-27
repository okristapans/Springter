package com.springter.business.service.impl;

import com.springter.business.repository.PostRepository;
import com.springter.business.repository.model.PostDAO;
import com.springter.business.service.PostService;
import com.springter.mappers.impl.PostMapperImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private PostMapperImpl mapper;

    @Override
    public List<Post> getAllPosts() {
        List<PostDAO> posts = repository.findAll();
        return posts.stream()
                .map(mapper::toPost)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        List<PostDAO> posts = repository.findAllByUserId(userId);
        return posts.stream()
                .map(mapper::toPost)
                .collect(Collectors.toList());
    }

    @Override
    public PostDAO postTweet(Post post) {
        return repository.save(mapper.toPostDAO(post));
    }

    @Override
    public Optional<Post> findByPostId(Long id) {
        Optional<PostDAO> post = repository.findById(id);
        return post.map(mapper::toPost);

    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }


}
