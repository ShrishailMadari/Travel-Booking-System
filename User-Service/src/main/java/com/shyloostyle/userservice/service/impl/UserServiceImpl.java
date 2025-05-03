package com.shyloostyle.userservice.service.impl;

import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;
import com.shyloostyle.userservice.exception.UserAlreadyExistsException;
import com.shyloostyle.userservice.exception.UserNotFoundException;
import com.shyloostyle.userservice.mapper.UserMapper;
import com.shyloostyle.userservice.model.User;
import com.shyloostyle.userservice.repository.UserRepository;
import com.shyloostyle.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (!ObjectUtils.isEmpty(user.getEmail())) {
            return userMapper.userToUserResponseDTO(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        // Check if the user already exists
        User existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }

        // Map the DTO to the entity
        User user = userMapper.userRequestDTOToUser(userRequestDTO);

        // Hash the password before saving the user
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);

        // Save the user entity
        user = userRepository.save(user);

        // Map the saved entity back to DTO and return the response
        return userMapper.userToUserResponseDTO(user);
    }



    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO deleteUser(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO updatePartialUserDetails(Long id, UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO getUserByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }
}
