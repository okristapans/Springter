package com.springter.mappers;


import com.springter.business.repository.model.PostDAO;
import com.springter.model.Post;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface PostMapper {
    PostDAO toDao(Post post);
    Post toPost(PostDAO postDAO);
}
