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
    @Size(max = 2, message = "max size 200")
    @NotBlank(message = "Name is mandatory")
    private String body;
    private User user;

}
