package com.springter.web;

import com.springter.business.repository.model.PostDAO;
import com.springter.business.service.impl.PostServiceImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SpringerController {
    @Autowired
    private PostServiceImpl service;

    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<PostDAO> getPost(@PathVariable Long id){
        return service.findByPostId(id);
    }

    @GetMapping("/user/{id}")
    public List<PostDAO> getAllUserPosts(@PathVariable Long id) {
        System.out.println(id);
        return service.findByUserId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> post(@RequestBody Post post) {
        service.postTweet(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

}
