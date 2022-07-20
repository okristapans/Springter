package com.springter.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Post {
    private Long id;
    private String body;
    private User user;

}
