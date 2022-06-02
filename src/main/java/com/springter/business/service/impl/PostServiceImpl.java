package com.springter.business.service.impl;

import com.springter.business.repository.SpringerRepository;
import com.springter.business.repository.model.PostDAO;
import com.springter.business.service.PostService;
import com.springter.mappers.PostMapperImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private SpringerRepository repository;
    @Autowired
    private PostMapperImpl mapper;
    @Override
    public List<PostDAO> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public PostDAO postTweet(Post post) {
        return repository.save(mapper.toDao(post));
    }

}
