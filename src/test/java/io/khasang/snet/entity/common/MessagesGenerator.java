package io.khasang.snet.entity.common;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import io.khasang.snet.util.Generator;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Random;

@Component
public class MessagesGenerator implements Generator<Message> {

    private Random random;
    private Generator<String> stringGenerator;

    public MessagesGenerator() {
        random = new Random(47);
        stringGenerator = new StringGenerator();
    }

    @Override
    public Message create() {
        return new Message(random.nextLong(), new Chat(), stringGenerator.create(), new GregorianCalendar());
    }

}
