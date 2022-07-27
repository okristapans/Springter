package com.springter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Size(max = 280, message = "Post cannot be longer than 280 characters!")
    @NotBlank(message = "Body cannot be empty!")
    private String body;
    private Long userId;

}
