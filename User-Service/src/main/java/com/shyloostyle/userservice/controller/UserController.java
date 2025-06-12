package com.shyloostyle.userservice.controller;

import com.shyloostyle.userservice.dto.ApiResponseDTO;
import com.shyloostyle.userservice.dto.UserRegisterDTO;
import com.shyloostyle.userservice.dto.UserRequestDTO;
import com.shyloostyle.userservice.dto.UserResponseDTO;
import com.shyloostyle.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Define your endpoints here

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> registerUser(@RequestBody @Valid UserRegisterDTO request) {
        userService.registerUser(request);
        return new ResponseEntity<>(new ApiResponseDTO("User registered successfully", 201), HttpStatus.CREATED);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@Valid @PathVariable Long userId) {
        UserResponseDTO userResponseDTO = userService.getUserById(userId);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/emailId")
    public ResponseEntity<UserResponseDTO> getUserByEmailId(@Valid @PathVariable String emailId) {
        UserResponseDTO userResponseDTO = userService.getUserByEmailId(emailId);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @PathVariable Long userId,@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(userId, userRequestDTO);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> updatePartialUserDetails(@Valid @PathVariable Long userId, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updatePartialUserDetails(userId, userRequestDTO);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable Long userId) {
        String response = userService.deleteUser(userId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/search")
    public ResponseEntity<UserResponseDTO> getUserByFirstNameAndLastName(@Valid @RequestParam String firstName, @Valid @RequestParam String lastName) {
        UserResponseDTO userResponseDTO = userService.getUserByFirstNameAndLastName(firstName, lastName);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOs = userService.getAllUsers();
        if (userResponseDTOs != null && !userResponseDTOs.isEmpty()) {
            return new ResponseEntity<>(userResponseDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }








}
