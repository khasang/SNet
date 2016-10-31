package io.khasang.snet.repository;


import io.khasang.snet.entity.userReg.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
