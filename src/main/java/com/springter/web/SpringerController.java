package com.springter.web;

import com.springter.business.repository.model.PostDAO;
import com.springter.business.service.impl.PostServiceImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpringerController {
    @Autowired
    private PostServiceImpl service;

    @GetMapping
    public List<PostDAO> getAllPosts(){
        return service.getAllPosts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> post(@RequestBody Post post, BindingResult bindingResult) {
        PostDAO newPost = service.postTweet(post);
        System.out.println(bindingResult);
        System.out.println(newPost.toString());
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


}
