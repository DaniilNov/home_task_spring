package com.example.home_task_spring.controller;

import com.example.home_task_spring.model.UserDto;
import com.example.home_task_spring.service.UserService;
import com.example.home_task_spring.validation.Create;
import com.example.home_task_spring.validation.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Slf4j
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getById(id);
        ResponseCookie userId = ResponseCookie.from("id", userDto.getId().toString()).maxAge(6000).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, userId.toString())
                .body(userDto);
    }

    @Validated(Create.class)
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @Validated(Update.class)
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    //    @DeleteMapping("/{id}")
    @DeleteMapping("/")
    public void deleteUser(@CookieValue Integer id, @RequestHeader Map<String, String> headers) {
        log.info("Headers: {}", headers);
//    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }
}
