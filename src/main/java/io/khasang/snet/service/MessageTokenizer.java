package io.khasang.snet.service;


import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/* Function of this utility: parse object by JsonSerializer
* and work with database */
@Component
public class MessageTokenizer {

    @Autowired
    private JsonSerializer<Message> messageSerializer;

    @Autowired
    private JsonSerializer<Chat> chatJsonSerializer;

    @Autowired
    private AbstractCRUD<Message> dataUtilMessages;

    @Autowired
    private UserDAO userDAO;


    public String getList(String request) {
        Message message = new Message();

        try {
            Chat chat = chatJsonSerializer.parseToEntity(request,Chat.class);
            message.setChat(chat);
            List<Message> messages = dataUtilMessages.getAll(message);
            Collections.sort(messages);
            return messageSerializer.parseToJson(messages);
        } catch (Exception exc) {
            return String.format("Error occur while quering list of messages. %s",exc.getMessage());
        }
    }

    public String addNew(String raw, User sender) {
        try {
            Message message = messageSerializer.parseToEntity(raw, Message.class);
            sender = userDAO.getUserByName(sender.getLogin());
            message.setSender(sender);
            dataUtilMessages.add(message);
            return messageSerializer.parseToJson(message);
        } catch (Exception exc) {
            return String.format("Error occur while saving message: %s", exc.getMessage());
        }
    }

    public String getOne(String raw) {
        try {
            Message message = messageSerializer.parseToEntity(raw, Message.class);
            message = dataUtilMessages.get(message);
            return messageSerializer.parseToJson(message);
        } catch (Exception exc) {
            return String.format("Error occur while getting existed message: %s", exc.getMessage());
        }
    }

    public String delete(String raw) {
        try {
            Message message = messageSerializer.parseToEntity(raw, Message.class);
            dataUtilMessages.delete(message);
        } catch (Exception exc) {
            return String.format("Error occur while deleting message: %s", exc.getMessage());
        }
        return "";
    }
}
