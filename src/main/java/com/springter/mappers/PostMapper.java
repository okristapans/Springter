package com.springter.mappers;


import com.springter.business.repository.model.PostDAO;
import com.springter.business.repository.model.UserDAO;
import com.springter.model.Post;
import com.springter.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


//@Mapper( componentModel = "spring")
public interface PostMapper {
    Post toPost(PostDAO postDAO);

    User toUser(UserDAO userDAO);

    UserDAO toUserDAO (User user);

    PostDAO toPostDAO (Post post);

}
