package com.vishwa.springboot.service.impl;

import com.vishwa.springboot.dto.UserDto;
import com.vishwa.springboot.entity.User;
import com.vishwa.springboot.exception.EmailExistsException;
import com.vishwa.springboot.exception.ResourceNotFoundException;
import com.vishwa.springboot.mapper.AutoUserMapper;
import com.vishwa.springboot.mapper.UserMapper;
import com.vishwa.springboot.repository.UserRepository;
import com.vishwa.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@ComponentScan("com.vishwa.springboot.mapper.UserMapper")
public class UserServiceImpl implements UserService {

    // No need to use @Autowired when we have only one constructor
    // Field Injection is bad to use so use constructor injection
    private UserRepository userRepository;

    private UserMapper userMapper;

    private ModelMapper modelMapper;

//    public UserServiceImpl(UserRepository userRepository)
//    {
//        this.userRepository = userRepository;
//    }


    @Override
    public UserDto createUser(UserDto user) {
        // Using Model Mapper convert Dto to Entity
        Optional<User> getUser = userRepository.findByEmail(user.getEmailAddress());

        if(getUser.isPresent()) {
            throw new EmailExistsException("Email already exists for user");
        }
        User newUser = modelMapper.map(user, User.class);

        userRepository.save(newUser);
        return userMapper.mapUsertoDto(newUser);
    }

    @Override
    public UserDto getUser(Long id) {
        User user =  userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return AutoUserMapper.mapper.mapUsertoDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        User updateUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        updateUser.setEmail(user.getEmailAddress());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        userRepository.save(updateUser);
        return userMapper.mapUsertoDto(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.deleteById(id);
    }
}
