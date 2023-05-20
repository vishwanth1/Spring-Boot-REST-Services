package com.vishwa.springboot.service;

import com.vishwa.springboot.dto.UserDto;
import com.vishwa.springboot.entity.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto user);

    public UserDto getUser(Long id);

    public List<UserDto> getUsers();

    public UserDto updateUser(Long id, UserDto user);

    public void deleteUser(Long id);

}
