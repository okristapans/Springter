package com.springter.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private Long id;
    private String username;
    private String password;
    private List<Post> posts;
}
