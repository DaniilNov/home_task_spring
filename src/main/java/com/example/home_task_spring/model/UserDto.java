package com.example.home_task_spring.model;

import com.example.home_task_spring.validation.Create;
import com.example.home_task_spring.validation.Email;
import com.example.home_task_spring.validation.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {

    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    Integer id;

    @NotBlank
    String name;

    @Email
    String email;
}
