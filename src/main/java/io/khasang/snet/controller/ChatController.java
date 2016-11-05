package io.khasang.snet.controller;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.ChatJsonTokenizer;
import io.khasang.snet.service.MessageTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ChatJsonTokenizer chatJsonTokenizer;

    @Autowired
    private MessageTokenizer messageTokenizer;

    @RequestMapping(value = "/chats/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getChats() {
        User user = userDAO.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return chatJsonTokenizer.getList(user);
    }

    @RequestMapping(value = "/chats/current", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getOneChat(@RequestBody String json) {
        return chatJsonTokenizer.getOne(json);
    }

    @RequestMapping(value = "/messages/list", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getMessagesList(@RequestBody String json) {
        return messageTokenizer.getList(json);
    }
}
