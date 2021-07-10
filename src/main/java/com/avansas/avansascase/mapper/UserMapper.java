package com.avansas.avansascase.mapper;

import com.avansas.avansascase.dto.UserDto;
import com.avansas.avansascase.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Named("toEntity")
    User userDtoToUser(UserDto userDto);

    @Named("toDto")
    UserDto userToUserDto(User user);

    @IterableMapping(qualifiedByName = "toDto")
    List<UserDto> usersToUserDtos(List<User> users);
}
