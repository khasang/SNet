package io.khasang.snet.service;

import io.khasang.snet.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer extends JsonSerializer<Message> {
    public MessageSerializer() {
        super();
    }
}
