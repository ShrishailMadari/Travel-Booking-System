package com.shyloostyle.userservice.model;


import com.shyloostyle.userservice.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User {

    @Id
    private Long id;  // Mandatory field

    private String firstName;  // Mandatory field

    private String lastName;  // Mandatory field

    @Column(nullable = false, unique = true)
    private String email;  // Mandatory field

    private String password;  // Mandatory field

    private String phoneNumber;  // Optional field

    private String address;  // Optional field

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;  // Optional, Default role USER

    @Column(nullable = true)
    private boolean isActive = true;  // Optional field, Default is active

    @Column(nullable = true)
    private String profilePicture;  // Optional field

    // Other fields as needed...

}

