package io.khasang.snet.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.khasang.snet.entity.Message;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MessageSerializer extends JsonSerializer<Message> {
    public MessageSerializer() {
        super();
    }
}
