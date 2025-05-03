package com.shyloostyle.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

