package com.shyloostyle.userservice.repository;

import com.shyloostyle.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByFirstNameAndLastName(String firstName, String lastName);
}
