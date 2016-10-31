package io.khasang.snet.repository;

import io.khasang.snet.entity.userReg.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
