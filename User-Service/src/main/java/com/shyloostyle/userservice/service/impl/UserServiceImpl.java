package com.shyloostyle.userservice.service.impl;

import com.shyloostyle.userservice.dto.UserRegisterDTO;
import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;
import com.shyloostyle.userservice.exception.UserAlreadyExistsException;
import com.shyloostyle.userservice.exception.UserNotFoundException;
import com.shyloostyle.userservice.mapper.UserMapper;
import com.shyloostyle.userservice.model.User;
import com.shyloostyle.userservice.repository.UserRepository;
import com.shyloostyle.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


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
    public UserResponseDTO getUserByEmailId(String email) {
        User user = userRepository.findByEmail(email);
        if (!ObjectUtils.isEmpty(user.getEmail())) {
            return userMapper.userToUserResponseDTO(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        // Check if the user already exists
        User existingUser = userRepository.findByEmail(userRegisterDTO.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }
//        If no user is found (existingUser == null), then the flow continues:
        // Map the DTO to the entity
        User user = userMapper.userRegisterDTOToUser(userRegisterDTO);

//         Hash the password before saving the user
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
        user.setPassword(hashedPassword);

        // Save the user entity
        user = userRepository.save(user);

        // Map the saved entity back to DTO and return the response
        return userMapper.userToUserResponseDTO(user);
    }



    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        // Fetch the existing user or throw an exception
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        // Update only non-null and non-empty fields from DTO
        if (!ObjectUtils.isEmpty(userRequestDTO.getFirstName())) {
            existingUser.setFirstName(userRequestDTO.getFirstName());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getLastName())) {
            existingUser.setLastName(userRequestDTO.getLastName());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getEmail())) {
            // Check if email is changing and is unique
            User userByEmail = userRepository.findByEmail(userRequestDTO.getEmail());
            if (userByEmail != null && !userByEmail.getId().equals(id)) {
                throw new UserAlreadyExistsException("Another user already exists with this email");
            }
            existingUser.setEmail(userRequestDTO.getEmail());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userRequestDTO.getPassword()));
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getPhoneNumber())) {
            existingUser.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getAddress())) {
            existingUser.setAddress(userRequestDTO.getAddress());
        }

//        // Optional fields like role or profilePicture
//        if (userRequestDTO.getRole() != null) {
//            existingUser.setRole(userRequestDTO.getRole());
//        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getProfilePicture())) {
            existingUser.setProfilePicture(userRequestDTO.getProfilePicture());
        }

        // Save the updated user
        User updatedUser = userRepository.save(existingUser);

        // Return response
        return userMapper.userToUserResponseDTO(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {
        // Fetch the user by ID or throw UserNotFoundException if not found
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Delete the user
        userRepository.delete(user);

        // Return a success message
        return "User with ID " + id + " successfully deleted.";
    }


    @Override
    public UserResponseDTO updatePartialUserDetails(Long id, UserRequestDTO userRequestDTO) {
        // Fetch the existing user or throw an exception
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        // Update only non-null and non-empty fields from DTO
        if (!ObjectUtils.isEmpty(userRequestDTO.getFirstName())) {
            existingUser.setFirstName(userRequestDTO.getFirstName());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getLastName())) {
            existingUser.setLastName(userRequestDTO.getLastName());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getPhoneNumber())) {
            existingUser.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getAddress())) {
            existingUser.setAddress(userRequestDTO.getAddress());
        }

        if (!ObjectUtils.isEmpty(userRequestDTO.getProfilePicture())) {
            existingUser.setProfilePicture(userRequestDTO.getProfilePicture());
        }

        // Save the updated user
        User updatedUser = userRepository.save(existingUser);

        // Return response
        return userMapper.userToUserResponseDTO(updatedUser);

    }

    @Override
    public UserResponseDTO getUserByFirstNameAndLastName(String firstName, String lastName) {
        User byFirstNameAndLastName = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!ObjectUtils.isEmpty(byFirstNameAndLastName)) {
            return userMapper.userToUserResponseDTO(byFirstNameAndLastName);
        }
        else throw new UserNotFoundException("User not found");

    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return userMapper.usersToUserResponseDTOs(users);
        } else {
            throw new UserNotFoundException("No users found");
        }
    }
}
