package io.khasang.snet.optional;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.service.QueryHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.PreparedStatement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CreateUsersTableInstance {
    @Autowired
    private QueryHandler queryHandler;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /* Drop old roles table and creates new one.
    * WARNING: All data will be loss!
    * */
    @Test
    @Ignore
    public void createNewTableRoles() {
        String droping = "DROP TABLE IF EXISTS roles";
        String create = "CREATE TABLE roles ( id integer NOT NULL, role_name character varying(255), " +
                "CONSTRAINT p_key PRIMARY KEY (id))";
        try {
            queryHandler.execute(droping);
            queryHandler.execute(create);
        } catch (Exception exc) {
            Assert.fail("Failed execute statement, error was thrown: " + exc);
        }

    }

    /* Drop old users table and creates new one.
    * WARNING: All data will be loss!
    * */
    @Test
    @Ignore
    public void createNewTableUsers() {
        String droping = "DROP TABLE IF EXISTS users";
        String creation = "CREATE TABLE users(  id integer NOT NULL,  login character varying(255),  " +
                "password character varying(255),  role_id integer,  CONSTRAINT users_pkey PRIMARY KEY (id))";
        try {
            queryHandler.execute(droping);
            queryHandler.execute(creation);
        } catch (Exception exc) {
            Assert.fail("Failed execute statement, error was thrown: " + exc);
        }
    }

    /* Creates administrator's role */
    @Test
    @Ignore
    public void createAdminRole() {
        int rowAffected = queryHandler.executeUpdate(createRolePSCreator(1,"ROLE_ADMIN"));
        Assert.assertEquals("Unable create admin role",1,rowAffected);
    }

    /* Creates administrator user account */
    @Test
    @Ignore
    public void createPrimeEntry() {
        Assert.assertEquals("Unable to create prime entry",1,createUser(1,"admin","admin",1));
    }

    /* Creates example user account */
    @Test
    @Ignore
    public void createExampleUser() {
        Assert.assertEquals("Unable to create example user",1,createUser(2,"user","example",1));
    }

    private int createUser(int id, String login, String password, int role) {
        String encryptedPwd = bCryptPasswordEncoder.encode(password);
        return queryHandler.executeUpdate(createUserPSCreator(login,encryptedPwd,role,id));
    }

    /* Creates PreparedStatementCreator for a new user account insertion
    * @param login: user's login in base
    * @param encryptedPwd: encrypted password
    * @param role: role id
    * @param id: user's id
    * @return PreparedStatementCreator instance
    * */
    private PreparedStatementCreator createUserPSCreator(String login, String encryptedPwd,int role, int id) {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
            statement.setInt(1,id);
            statement.setString(2,login);
            statement.setString(3,encryptedPwd);
            statement.setInt(4,role);
            return statement;
        };
    }

    /* Creates PreparedStatementCreator for a new role insertion
    * @param id: role's id
    * @param roleName: role's name
    * @return PreparedStatementCreator instance
    * */
    private PreparedStatementCreator createRolePSCreator(int id, String roleName) {
        return connection -> {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO roles VALUES (?,?)");
            statement.setInt(1,id);
            statement.setString(2,roleName);
            return statement;
        };
    }

}
