package io.khasang.snet.controller;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class UsersPasswordChanger {
    private QueryHandler queryHandler;

    public UsersPasswordChanger(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    public String change(String login, String newPassword) {
        int res = queryHandler.executeUpdate(this.preparedStatementCreate(newPassword, login));
        if (res > 0) return String.format("Row affected %d", res);
        else return "Fail to change password";
    }

    /* Creation PreparedStatementCreator implementation
    *  @param updating value
    *  @param key value
    *  */
    private PreparedStatementCreator preparedStatementCreate(String value, String key) {
        return connection -> {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE users SET users.password = ? WHERE users.login = ?");
            statement.setObject(1,value);
            statement.setObject(2,key);
            return statement;
        };
    }
}
