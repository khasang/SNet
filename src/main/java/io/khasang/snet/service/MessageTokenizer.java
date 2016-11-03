package io.khasang.snet.service;


import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* Function of this utility: parse object by JsonSerializer
* and work with database */
@Component
public class MessageTokenizer {

    @Autowired
    private JsonSerializer<Message> messageSerializer;

    @Autowired
    private AbstractCRUD<Message> dataUtilMessages;

    public String getList(Chat chat) {
        Message message = new Message();
        message.setChat(chat);

        try {
            return messageSerializer.parseToJson(dataUtilMessages.getAll(message));
        } catch (Exception exc) {
            return String.format("Error occur while quering list of messages. %s",exc.getMessage());
        }
    }

    public String addNew(String raw) {
        try {
            Message message = messageSerializer.parseToEntity(raw,Message.class);
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
