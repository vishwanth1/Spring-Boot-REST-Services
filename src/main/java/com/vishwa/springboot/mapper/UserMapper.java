package com.vishwa.springboot.mapper;

import com.vishwa.springboot.dto.UserDto;
import com.vishwa.springboot.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUsertoDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public User mapDtoEntity(UserDto user) {
        return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }
}
