package com.shyloostyle.userservice.service;

import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;

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

    UserResponseDTO getUserByEmail(String email);

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO deleteUser(Long id);

    UserResponseDTO updatePartialUserDetails(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO getUserByFirstNameAndLastName(String firstName, String lastName);


}
