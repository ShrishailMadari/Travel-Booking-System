package com.shyloostyle.userservice.mapper;


import com.shyloostyle.userservice.dto.UserRegisterDTO;
import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;
import com.shyloostyle.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {

    // Create an instance of the mapper
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapping from UserRequestDTO to User (for creating a new user)
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);

    User userRegisterDTOToUser(UserRegisterDTO userRegisterDTO);

    // Optional: If you want to map back from User to UserRequestDTO
    UserRequestDTO userToUserRequestDTO(User user);

    //userResponseDTOToUser
    // Optional: If you want to map from User to UserResponseDTO
     UserResponseDTO userToUserResponseDTO(User user);

    List<UserResponseDTO> usersToUserResponseDTOs(List<User> users);
}

