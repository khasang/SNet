package io.khasang.snet.dao.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.User;

public interface UserDAO {

    void addUser(User user);
    User getUserByName(String name);
    User getUserById(int id);
    void updateUser(User user);
}
