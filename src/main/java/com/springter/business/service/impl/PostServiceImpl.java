package com.springter.business.service.impl;

import com.springter.business.repository.SpringerRepository;
import com.springter.business.repository.model.PostDAO;
import com.springter.business.service.PostService;
import com.springter.mappers.PostMapperImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private SpringerRepository repository;
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
    public List<PostDAO> findByUserId(Long userId) {
        List<PostDAO> posts = repository.getPosts(userId);
        return posts;
    }

    @Override
    public PostDAO postTweet(Post post) {
        return repository.save(mapper.toDao(post));
    }

    @Override
    public Optional<PostDAO> findByPostId(Long id) {
        return repository.findById(id);

    }


}
