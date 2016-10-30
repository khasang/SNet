package io.khasang.snet.dao.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.User;

public interface UserDAO {

    void addUser(User user, AuthRules authRules);
    User getUserByName(String name);
    void updateUser(User user);
}
