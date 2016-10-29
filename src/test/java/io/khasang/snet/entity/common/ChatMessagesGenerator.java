package io.khasang.snet.entity.common;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ChatMessagesGenerator implements Generator<Chat> {
    private Random random = new Random(3);

    private String chooseMessage(){
        String first = "First message";
        String second = "Second message";
        String third = "Third message";
        String message = "";
        switch (random.nextInt(3)){
            case 0: message = first;
                break;
            case 1: message = second;
                break;
            case 2: message = third;
                break;
        }
        return message;
    }

    @Override
    public Chat create() {
        return new Chat(chooseMessage());

    }
}
