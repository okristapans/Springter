package com.springter.business.service.impl;


import com.springter.business.repository.PostRepository;
import com.springter.business.repository.model.PostDAO;
import com.springter.mappers.impl.PostMapperImpl;
import com.springter.model.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith({MockitoExtension.class})
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostServiceImpl postService;
    @Mock
    private PostMapperImpl mapper;

    @Test
    void returnUserPosts() {

        List<PostDAO> testList = TestData.repositoryPostList();
        System.out.println(testList.get(1).toString());
        List<Post> expected = TestData.mappedPostList();
        System.out.println(expected.get(1).toString());
        Mockito.when(postRepository.findAllByUserId(1L)).thenReturn(testList);
        Mockito.when(mapper.toPost(Mockito.any(PostDAO.class))).thenReturn(expected.get(1));
        List<Post> actualResponse = postService.findByUserId(1L);
        Assertions.assertThat(actualResponse.get(1).getUserId()).isEqualTo(expected.get(1).getUserId());

    }


}
