package com.avansas.avansascase.service;

import com.avansas.avansascase.dto.UserDto;
import com.avansas.avansascase.exception.EmailExistException;
import com.avansas.avansascase.exception.InvalidBirthDateException;
import com.avansas.avansascase.exception.PhoneNumberExistException;
import com.avansas.avansascase.mapper.UserMapper;
import com.avansas.avansascase.model.Role;
import com.avansas.avansascase.model.User;
import com.avansas.avansascase.repository.UserRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Email not found.");
        }
        return user;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) {
        checkIfEmailExists(userDto.getEmail());
        checkIfPhoneExists(userDto.getPhoneNumber());
        checkIfBirthDateValid(userDto.getBirthDate());
        User user = userMapper.userDtoToUser(userDto);
        Role role = new Role();
        role.setUser(user);
        role.setAuthority("ROLE_" + userDto.getRole().toString());
        user.setAuthorities(Sets.newHashSet(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        UserDto userToUpdate = findUserById(userDto.getId());
        if(!userToUpdate.getEmail().equals(userDto.getEmail())){
            checkIfEmailExists(userDto.getEmail());
        }
        if(!userToUpdate.getPhoneNumber().equals(userDto.getPhoneNumber())){
            checkIfPhoneExists(userDto.getPhoneNumber());
        }
        checkIfBirthDateValid(userDto.getBirthDate());
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private void checkIfEmailExists(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailExistException();
        }
    }

    private void checkIfPhoneExists(String phoneNumber){
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new PhoneNumberExistException();
        }
    }

    private void checkIfBirthDateValid(Date birthDate){
        if(birthDate.before(new GregorianCalendar(1900, Calendar.FEBRUARY, 1).getTime())){
            throw new InvalidBirthDateException();
        }
    }
}
