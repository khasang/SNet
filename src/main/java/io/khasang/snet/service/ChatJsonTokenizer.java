package io.khasang.snet.service;


import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatJsonTokenizer {

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;

    @Autowired
    private JsonSerializer<Chat> chatSerializer;

    public String getList(User user) {
        try {
            return chatSerializer.parseToJson(chatCRUD.getListSearched(user));
        } catch (RuntimeException exc) {
            return String.format("Error occur while quering chat list: %s", exc.getMessage());
        }
    }

    public String saveNew(String raw, User user) {
        try {
            Chat chat = chatSerializer.parseToEntity(raw, Chat.class);
            chatCRUD.add(chat);
            ChatRegistryUnit reg = new ChatRegistryUnit(chat, user);
            registryCRUD.add(reg);
            return chatSerializer.parseToJson(chat);
        } catch (Exception exc) {
            return String.format("Error while saving chat occurs: %s", exc.getMessage());
        }
    }

    public String getOne(String raw) {
        try {
            Chat chat = chatSerializer.parseToEntity(raw, Chat.class);
            chat = chatCRUD.get(chat);
            return chatSerializer.parseToJson(chat);
        } catch (RuntimeException exc) {
            return String.format("Error while getting existed chat occurs: %s", exc.getMessage());
        }
    }

    public String deleteExists(String raw, User user) {
        try {
            Chat chat = chatSerializer.parseToEntity(raw, Chat.class);
            chatCRUD.delete(chat);
            ChatRegistryUnit reg = new ChatRegistryUnit(chat,user);
            registryCRUD.add(reg);
        } catch (Exception exc) {
            return String.format("Error while deleting chat occurs: %s", exc.getMessage());
        }
        return "";
    }
}
