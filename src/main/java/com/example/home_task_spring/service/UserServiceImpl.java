package com.example.home_task_spring.service;

import com.example.home_task_spring.model.UserDto;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserServiceImpl implements UserService {

    private final Map<Integer, UserDto> userDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        Integer id = sequenceId.getAndIncrement();
        userDtoMap.put(id, UserDto.builder().id(id).email("123@mail.com").name("Name").build());
    }

    @Override
    public List<UserDto> getAll() {
        return new ArrayList<>(userDtoMap.values());
    }

    @Override
    public UserDto getById(Integer id) {
        return userDtoMap.get(id);
    }

    @Override
    public UserDto create(UserDto userDto) {
        Integer id = sequenceId.getAndIncrement();
        userDto.setId(id);
        userDtoMap.put(id, userDto);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserDto userDtoFromMap = userDtoMap.get(userDto.getId());
        if (userDtoFromMap == null) {
            return null;
        } else {
            userDtoMap.put(userDto.getId(), userDto);
            return userDto;
        }
    }

    @Override
    public void delete(Integer id) {
        userDtoMap.remove(id);
    }
}
