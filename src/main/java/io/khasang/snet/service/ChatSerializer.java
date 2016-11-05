package io.khasang.snet.service;


import io.khasang.snet.entity.Chat;
import org.springframework.stereotype.Component;


@Component
public class ChatSerializer extends JsonSerializer<Chat> {

    public ChatSerializer() {
        super();
    }
}
