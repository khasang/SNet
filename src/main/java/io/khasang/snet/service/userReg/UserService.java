package io.khasang.snet.service.userReg;


import io.khasang.snet.entity.userReg.User;

public interface UserService  {

    void save(User user);

    User findByUsername(String username);
}
