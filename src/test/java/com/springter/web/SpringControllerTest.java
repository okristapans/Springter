package com.springter.web;

import com.springter.business.service.impl.PostServiceImpl;
import com.springter.model.Post;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SpringterController.class)
class SpringControllerTest {

    @MockBean
    PostServiceImpl postService;
    @Autowired
    SpringterController controller;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void returnPost() throws Exception {

        when(postService.findByPostId(1L)).thenReturn(Optional.of(new Post(1L, "Post Successful!", 2L)));
        mockMvc.perform(get("/api/post/1")).andDo(print()).andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Is.is("Post Successful!")));
    }

    @Test
    void returnPostInvalidId() throws Exception {

        when(postService.findByPostId(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/post/1")).andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    void whenPostValidPost() throws Exception {
        String user = "{\n" +
                "    \"body\": \"Hello, World!\",\n" +
                "    \"userId\": 2\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Is.is("Hello, World!")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenReturnValidUserPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "123", 1L));
        posts.add(new Post(2L, "456", 1L));

        when(postService.findByUserId(1L)).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenPostInValidPost() throws Exception {
        String user = "{\n" +
                "    \"body\": \"\",\n" +
                "    \"userId\": 2\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Is.is("Body cannot be empty!")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenPostInValidPost280() throws Exception {
        String user = "{\n" +
                "    \"body\": \"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\",\n" +
                "    \"userId\": 2\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body", Is.is("Post cannot be longer than 280 characters!")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deletePost() throws Exception {

        mockMvc.perform(delete("/api/delete/1337"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Is.is("Deleted post with id yeet")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void deletePostInvalid() throws Exception {
        String user = "{\n" +
                "    \"body\": \"l33t\",\n" +
                "    \"userId\": 2\n" +
                "}";
        Post post = new Post(1L, "l33t", 420L);

        mockMvc.perform(delete("/api/delete/1337"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Is.is("No post with id 1337 exists")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));

    }


}

