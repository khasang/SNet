package io.khasang.snet.repository;


import io.khasang.snet.entity.userReg.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
