package io.khasang.snet.controller.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.RolesList;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.userauth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView addNewUser(@ModelAttribute ("user") User user){
        String message="Something going wrong";
        try {
            if (userService.getUserByLogin(user.getLogin()) != null){
                message = user.getLogin();
                return new ModelAndView("/register", "message", message );
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.addUser(user);
            User us = userService.getUserByLogin(user.getLogin());
            Roles role = userService.getRolesByName(RolesList.ROLE_USER.toString());
            AuthRules authRules = new AuthRules();
            authRules.setUser_id(us.getID());
            authRules.setRole_id(role.getId());
            userService.addAuthRules(authRules);
            message = "You successfully register!";
            return new ModelAndView("endRegistration","message",message);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("endRegistration","message",message);
        }
    }
}
