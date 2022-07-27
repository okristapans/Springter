package com.springter.web;

import com.springter.business.service.impl.PostServiceImpl;
import com.springter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SpringterController {

    @Autowired
    private PostServiceImpl service;

    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/post/{id}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Optional<Post>> getPost(@PathVariable Long id) {
        Optional<Post> post = service.findByPostId(id);
        if (post.isPresent()) {
            return new ResponseEntity<>(post, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(post, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts/user/{id}")
    public ResponseEntity<List<Post>> getAllUserPosts(@PathVariable Long id) {
        List<Post> posts = service.findByUserId(id);
        if (!posts.isEmpty()) {

            return new ResponseEntity<>(posts, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> post(@Valid @RequestBody Post post) {
        service.postTweet(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        service.deletePost(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
