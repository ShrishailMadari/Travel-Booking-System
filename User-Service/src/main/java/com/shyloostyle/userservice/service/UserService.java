package com.shyloostyle.userservice.service;

import com.shyloostyle.userservice.dto.UserRegisterDTO;
import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;

import java.util.List;

/*
HTTP Method | URL | Description
POST | /api/users/register | Register a new user
GET | /api/users/{userId} | Get user details by ID
GET | /api/users | Get all users (with pagination, optional search)
GET | /api/users/search?name={name} | Get users by name
PUT | /api/users/{userId} | Update full user details
PATCH | /api/users/{userId} | Update partial user details
DELETE | /api/users/{userId} | Soft delete / deactivate a user
*/
public interface UserService {
    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByEmailId(String emailId);

    UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    String deleteUser(Long id);

    UserResponseDTO updatePartialUserDetails(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO getUserByFirstNameAndLastName(String firstName, String lastName);

    List<UserResponseDTO> getAllUsers(); // Pagination and sorting



}
