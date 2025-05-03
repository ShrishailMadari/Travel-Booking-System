package com.shyloostyle.userservice.model;


import com.shyloostyle.userservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

