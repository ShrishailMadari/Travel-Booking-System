package com.shyloostyle.userservice.dto;

import com.shyloostyle.userservice.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {

    private Long id;  // Automatically generated, return it

    private String firstName;  // From entity

    private String lastName;  // From entity

    private String email;  // From entity

    private String phoneNumber;  // Optional, from entity

    private String address;  // Optional, from entity

    private Boolean isActive;  // From entity

    private String profilePicture;  // Optional, from entity

    private UserRole role;  // From entity

    // Getters and Setters
}

