package com.kit.backend.auth.UsersDAO;

import com.kit.backend.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
