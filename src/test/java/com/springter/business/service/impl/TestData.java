package com.springter.business.service.impl;

import com.springter.business.repository.model.PostDAO;
import com.springter.business.repository.model.UserDAO;
import com.springter.mappers.impl.PostMapperImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestData {
    @Autowired
    PostMapperImpl mapper;
    static List<PostDAO> repositoryPostList(){
        List<PostDAO> repositoryList = new ArrayList<>();
        List<PostDAO> userPosts1 = new ArrayList<>();
        UserDAO user1 = new UserDAO(1L,"user1","password1", userPosts1);

        userPosts1.add(new PostDAO(1L,"123",user1));
        userPosts1.add(new PostDAO(2L,"second post",user1));
        userPosts1.add(new PostDAO(3L,"third post",user1));

        repositoryList.add(new PostDAO(1L,"first post",user1));
        repositoryList.add(new PostDAO(2L,"second post",user1));
        repositoryList.add(new PostDAO(3L,"third post",user1));

        return repositoryList;

    }

    static List<Post> mappedPostList(){
        List<Post> userMappedList = new ArrayList<>();
        userMappedList.add(new Post(1L,"123",1L));
        userMappedList.add(new Post(2L,"second post",1L));
        userMappedList.add(new Post(3L,"third post",1L));

        return userMappedList;
    }
}
