package io.khasang.snet.dao.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);

    User getUserByName(String name);

    User getUserById(int id);

    void updateUser(User user);

    List<User> getAllUsers(String login);

    List<User> getUsersByIdList(List<Long> idList);

    List<User> getUsersNotInIdList(List<Long> idList);
}
