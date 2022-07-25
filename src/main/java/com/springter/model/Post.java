package com.springter.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Post {
    private Long id;
    @Size(max = 280, message = "max size 280")
    @NotBlank(message = "Body cannot be empty!")
    private String body;
    private Long userId;

}
