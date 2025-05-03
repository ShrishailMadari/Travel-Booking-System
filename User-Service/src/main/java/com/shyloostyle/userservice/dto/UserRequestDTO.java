package com.shyloostyle.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * UserRequestDTO is a Data Transfer Object (DTO) that represents the data required to create or update a user.
 * It includes fields for the user's first name, last name, email, password, phone number, address, and profile picture.
 * The class uses validation annotations to enforce constraints on the input data.
 */

public class UserRequestDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;  // Mandatory

    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;  // Mandatory

    @NotNull
    @Email
    private String email;  // Mandatory

    @NotNull
    @Size(min = 6)
    private String password;  // Mandatory

    private String phoneNumber;  // Optional

    private String address;  // Optional

    private String profilePicture;  // Optional

    // Getters and Setters
}

