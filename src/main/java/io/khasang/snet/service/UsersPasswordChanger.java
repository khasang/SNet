package io.khasang.snet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class UsersPasswordChanger {

    private QueryHandler queryHandler;

    @Autowired
    public UsersPasswordChanger(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    public String change(String login, String newPassword) {
        int res = queryHandler.executeUpdate(this.preparedStatementCreate(login, newPassword));
        if (res > 0) return String.format("Row affected %d", res);
        else return "Fail to change password";
    }

    /* Creates PreparedStatementCreator implementation
    *  @param value: encrypted password
    *  @param key: login user in database
    *  @return PreparedStatementCreator instance
    *  */
    private PreparedStatementCreator preparedStatementCreate(String key, String value) {
        return connection -> {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE users SET password = ? WHERE login = ?");
            statement.setString(1,value);
            statement.setString(2,key);
            return statement;
        };
    }
}
