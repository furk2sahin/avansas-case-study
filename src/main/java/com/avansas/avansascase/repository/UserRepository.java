package com.avansas.avansascase.repository;

import com.avansas.avansascase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}
