package io.khasang.snet.service;


import io.khasang.snet.repository.AbstractCRUD;
import io.khasang.snet.repository.AbstractRegistrySearcher;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* This is utils for working with repository and JSON translators */
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

    public Chat getExistedDialog(User first, User second) {
        Chat existingChat = chatCRUD.getExistedDialog(first, second);
        if (existingChat != null) {
            return existingChat;
        } else {
            Chat chat = new Chat(String.format("%s, %s",first.getLogin(), second.getLogin()));
            chat = this.saveNewChat(chat);
            this.addUserIntoChat(first, chat);
            this.addUserIntoChat(second, chat);
            return chat;
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
    public String deleteExists(Chat chat, User user) {
        try {
            registryCRUD.delete(new ChatRegistryUnit(chat, user));
//            chatCRUD.delete(chat);
        } catch (Exception exc) {
            return String.format("Error while deleting chat occurs: %s", exc.getMessage());
        }
        return "";
    }

    private Chat saveNewChat(Chat chat) {
        chatCRUD.add(chat);
        return chat;
    }

    private void addUserIntoChat(User user, Chat chat) {
        ChatRegistryUnit registryUnit = new ChatRegistryUnit(chat, user);
        registryCRUD.add(registryUnit);
    }
}
