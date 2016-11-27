package io.khasang.snet.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import io.khasang.snet.entity.common.MessagesGenerator;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.ChatJsonTokenizer;
import io.khasang.snet.service.ChatSerializer;
import io.khasang.snet.service.MessageSerializer;
import io.khasang.snet.service.MessageTokenizer;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class JSONSerializationTest {

    private final Logger LOG = Logger.getLogger(JSONSerializationTest.class);

    @Autowired
    private Generator<Message> generator;

    @Autowired
    private Generator<Chat> chatGenerator;

    private ObjectMapper mapper;

    @Autowired
    private ChatJsonTokenizer chatJsonTokenizer;

    @Autowired
    private MessageSerializer messageSerializer;

    @Autowired
    private ChatSerializer chatSerializer;

    @Autowired
    private MessageTokenizer messageTokenizer;

    @Before
    public void setUp() {
        if (mapper==null) mapper = new ObjectMapper();
    }

    @Test
    public void pojoSerializationTest() {
        Message message = generator.create();
        try {
            String json = mapper.writeValueAsString(message);
            Message deSerialized = mapper.readValue(json,Message.class);
            assertEquals(message.toString(), deSerialized.toString());
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    public void listOfPOJOTest() {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messages.add(generator.create());
        }
        try {
            String json = mapper.writeValueAsString(messages);
            List<Message> deSerialized = mapper.readValue(json, new TypeReference<List<Message>>(){});
            deSerialized.sort((Message first, Message second) -> first.getStamp().compareTo(second.getStamp()));
            messages.sort((Message first, Message second) -> first.getStamp().compareTo(second.getStamp()));
            for (int i = 0; i < messages.size(); i++) {
                assertEquals(messages.get(i).toString(),deSerialized.get(i).toString());
            }
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void mappingJSONTest() {
        Message message = generator.create();
        try {
            String json = mapper.writeValueAsString(message);
            Map<String, Object> mapResult = mapper.readValue(json,Map.class);
            LOG.debug(String.format("Test for %s as result of parsing JSON",
                    mapResult.get("chat").getClass().getName()));
        } catch (IOException e) {
            LOG.error("Failed parsing of JSON sample", e);
            fail();
        }

    }

    @Test
    public void messageReadingTest() {
        Message message = generator.create();
        Chat chat = new Chat();
        chat.setID(1L);
        message.setChat(chat);
        User user = new User();
        user.setID(16);
        message.setSender(user);
        message.setID(33L);

        String raw = messageSerializer.parseToJson(message);

        LOG.debug(String.format("Object %s parse to JSON format: %s", Message.class, raw));
        LOG.debug(String.format("Raw JSON parsed back into object %s",
                messageSerializer.parseToEntity(raw,Message.class)));
    }

    @Test
    @Rollback
    @Transactional
    public void chatSavingTest() {
        Chat chat = chatGenerator.create();
        chat.setDescription("chatSavingTest");
        User user = new User();
        user.setID(16);

        String raw = chatSerializer.parseToJson(chat);
        chatJsonTokenizer.saveNew(raw,user);
    }

    @Test
    public void chatGetExistedTest() {
        Chat chat = chatGenerator.create();
        chat.setID(34L);

        String raw = chatSerializer.parseToJson(chat);
        raw = chatJsonTokenizer.getOne(raw);

        LOG.debug(String.format("Object %s parse to JSON format: %s", Chat.class, raw));
        LOG.debug(String.format("Raw JSON parsed back into object %s",
                chatSerializer.parseToEntity(raw,Chat.class)));
    }
}
