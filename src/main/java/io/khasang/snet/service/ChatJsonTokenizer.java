package io.khasang.snet.service;


import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* This is utils for working with dao and JSON translators */
@Component
public class ChatJsonTokenizer {

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;

    // JSON translator
    @Autowired
    private JsonSerializer<Chat> chatSerializer;

    /*
    * @param user: filter by chats by user */
    public String getList(User user) {
        try {
            return chatSerializer.parseToJson(chatCRUD.getListSearched(user));
        } catch (RuntimeException exc) {
            return String.format("Error occur while quering chat list: %s", exc.getMessage());
        }
    }

    /* @param raw take JSON string
    * @param user take user with id
    * method save chat and write new
    * registry unit
    * @return saved chat with id */
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

    // Returns chat quered by id
    public String getOne(String raw) {
        try {
            Chat chat = chatSerializer.parseToEntity(raw, Chat.class);
            chat = chatCRUD.get(chat);
            return chatSerializer.parseToJson(chat);
        } catch (RuntimeException exc) {
            return String.format("Error while getting existed chat occurs: %s", exc.getMessage());
        }
    }

    // deletes already existed entity
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
