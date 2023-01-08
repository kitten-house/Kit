package com.kit.backend.auth.UsersDAO;

import com.kit.backend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE users.googleId = ?1", nativeQuery = true)
    Optional<User> findByGoogleId(String id);

    @Query(value = "SELECT * FROM users WHERE users.id = ?1", nativeQuery = true)
    Optional<User> findById(long id);

}
