package com.avansas.avansascase.service;

import com.avansas.avansascase.dto.UserDto;
import com.avansas.avansascase.mapper.UserMapper;
import com.avansas.avansascase.model.Role;
import com.avansas.avansascase.model.User;
import com.avansas.avansascase.repository.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Username not exists with email: " + email);
        }
        return user;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        Role role = new Role();
        role.setUser(user);
        role.setAuthority("ROLE_" + userDto.getRole().toString());
        user.setAuthorities(Sets.newHashSet(role));
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        } else {
            return userMapper.userToUserDto(user);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
