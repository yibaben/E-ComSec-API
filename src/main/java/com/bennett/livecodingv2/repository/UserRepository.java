package com.bennett.livecodingv2.repository;

import com.bennett.livecodingv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserCode(String userCode);

    Optional<User> findUserByUsername(String username);
}
