package io.khasang.snet.entity.common;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class MessagesGenerator implements Generator<Message> {

    private Generator<String> stringGenerator;

    public MessagesGenerator() {
        stringGenerator = new StringGenerator();
    }

    @Override
    public Message create() {
        return new Message(new User(), new Chat(), stringGenerator.create());
    }

}
