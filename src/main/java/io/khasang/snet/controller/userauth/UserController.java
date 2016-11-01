package io.khasang.snet.controller.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.RolesList;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.userauth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addUser(@RequestBody User user, HttpServletResponse response) {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.addUser(user);
            User us = userService.getUserByLogin(user.getLogin());
            Roles role = userService.getRolesByName(RolesList.ROLE_USER.toString());
            AuthRules authRules = new AuthRules();
            authRules.setUser_id(us.getId());
            authRules.setRole_id(role.getId());
            userService.addAuthRules(authRules);
            return "Succesfully register user " + user.getLogin();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding user: " + e.getMessage();
        }
    }

}
