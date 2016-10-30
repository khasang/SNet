package io.khasang.snet.service.userauth;

import io.khasang.snet.dao.userauth.AuthRulesDAO;
import io.khasang.snet.dao.userauth.RolesDAO;
import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    AuthRulesDAO authRulesDAO;
    @Autowired
    RolesDAO rolesDAO;

    // User
    public void addUser(User user){
        userDAO.addUser(user);
    }

    public User getUserByLogin(String login){
        return userDAO.getUserByName(login);
    }

    public void updateUser(User user){
        userDAO.addUser(user);
    }

    //Auth rules
    public void addAuthRules(AuthRules authRules){
        authRulesDAO.addAuthRules(authRules);
    }

    public void deleteAllUserAuthRules(int user_id){
        authRulesDAO.deleteAllUserAuthRules(user_id);
    }

    public void deleteAllRolesAuthRules(int role_id){
        authRulesDAO.deleteAllRoleAuthRules(role_id);
    }

    public List<AuthRules> getAllUserAuthRules(int user_id){
        return authRulesDAO.getAllUserAuthRules(user_id);
    }

    // Roles
    public void addRole(Roles roles){
        rolesDAO.addRoles(roles);
    }

    public void deleteRoleById(int id){
        rolesDAO.deleteRolesById(id);
    }

    public void deleteRolesByName(String name){
        rolesDAO.deleteRolesByName(name);
    }
    public Roles getRolesById(int id){
        return  rolesDAO.getRolesById(id);
    }
    public Roles getRolesByName(String name){
        return rolesDAO.getRolesByName(name);
    }
}
