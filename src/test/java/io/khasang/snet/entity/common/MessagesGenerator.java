package io.khasang.snet.entity.common;

import io.khasang.snet.entity.Message;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class MessagesGenerator implements Generator<Message> {

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
    public Message create() {
        return new Message(random.nextLong(), random.nextLong(), chooseMessage(), new GregorianCalendar());
    }
}
