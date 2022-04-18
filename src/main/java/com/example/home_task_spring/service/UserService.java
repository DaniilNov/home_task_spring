package com.example.home_task_spring.service;



import com.example.home_task_spring.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Integer id);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void delete(Integer id);

}
