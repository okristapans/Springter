package com.springter.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class User {
    private Long id;
    private String userName;
    private String password;
    private List<Post> posts;
}
