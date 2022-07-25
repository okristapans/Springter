package com.springter.mappers.impl;

import com.springter.business.repository.model.PostDAO;
import com.springter.business.repository.model.UserDAO;
import com.springter.business.repository.model.UserRepository;
import com.springter.mappers.PostMapper;
import com.springter.model.Post;
import com.springter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {
    @Autowired
    UserRepository userRepository;
    @Override
    public Post toPost(PostDAO postDAO) {
        Post post = new Post();
        post.setBody(postDAO.getBody());
        post.setId(postDAO.getId());
        post.setUserId(postDAO.getUser().getId());
        return post;
    }

    @Override
    public User toUser(UserDAO userDAO) {
        User user = new User();
        user.setPassword(userDAO.getPassword());
        user.setUsername(userDAO.getUsername());
        user.setId(userDAO.getId());
        return user;
    }

    @Override
    public UserDAO toUserDAO(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(user.getUsername());
        userDAO.setPassword(user.getPassword());
        userDAO.setId(user.getId());
        return userDAO;
    }

    @Override
    public PostDAO toPostDAO(Post post) {
        PostDAO postDAO = new PostDAO();
        postDAO.setBody(post.getBody());
        postDAO.setUser(userRepository.getReferenceById(post.getUserId()));
        return postDAO;
    }

}
