package io.khasang.snet.demo;

import io.khasang.snet.repository.userauth.AuthRulesDAO;
import io.khasang.snet.repository.userauth.RolesDAO;
import io.khasang.snet.repository.userauth.UserDAO;
import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreatePrimeEntry {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthRulesDAO authRulesDAO;

    @Autowired
    private RolesDAO rolesDAO;

    /* Creation ADMIN user account */
    public void createPrimeEntry() {
        String login = "admin";
        String passwd = "admin";
        String primeRole = "ROLE_ADMIN";

        /* Creation admin account */
        User admin = new User();
        admin.setLogin(login);
        admin.setPassword(new BCryptPasswordEncoder().encode(passwd));
        userDAO.addUser(admin);

        /* Creation admin role */
        Roles prime = new Roles();
        prime.setRole(primeRole);
        rolesDAO.addRoles(prime);

        /* Creation of admin authorisation rule */
        AuthRules rule = new AuthRules();
        rule.setUser_id(admin.getID());
        rule.setRole_id(prime.getId());
        authRulesDAO.addAuthRules(rule);

    }
}
