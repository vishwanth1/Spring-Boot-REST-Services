package com.vishwa.springboot.mapper;

import com.vishwa.springboot.dto.UserDto;
import com.vishwa.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper mapper = Mappers.getMapper(AutoUserMapper.class);

    @Mapping(source = "email", target = "emailAddress")
    public UserDto mapUsertoDto(User user);

    public User mapDtoEntity(UserDto userDto);
}
