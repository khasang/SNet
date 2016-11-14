package io.khasang.snet.service;


import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class UserListJsonSerializer extends JsonSerializer<User> {

    public UserListJsonSerializer() {
        super();
    }
}
