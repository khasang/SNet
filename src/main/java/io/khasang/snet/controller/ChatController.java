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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/chat/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getChats() {
        User user = userDAO.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        return chatJsonTokenizer.getList(user);
    }

    @RequestMapping(value = "/chat/current", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getOneChat(@RequestBody String json) {
        return chatJsonTokenizer.getOne(json);
    }

    @RequestMapping(value = "/messages/list", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getMessagesList(@RequestBody String json) {
        return messageTokenizer.getList(json);
    }

    @RequestMapping(value = "/messages/new", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addNewMessage(@RequestBody String json) {
        User sender = new User();
        sender.setLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return messageTokenizer.addNew(json, sender);
    }

    @RequestMapping(value = "/newDialog", method = RequestMethod.GET)
    public ModelAndView newDialog(@RequestParam(value = "login") String login) {
        User receiver = userDAO.getUserByName(login);
        User sender = userDAO.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Chat chat = chatJsonTokenizer.getExistedDialog(sender, receiver);
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("chat_id", chat.getID());
        return modelAndView;
    }

    @RequestMapping(value = "/chat/remove", method = RequestMethod.POST)
    public Object removeOne(@RequestBody String chatId) {
        User current = userDAO.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Chat willRemoved = new Chat();
        try {
            willRemoved.setID(Long.parseLong(chatId));
        } catch (NumberFormatException exc) {
            return String.format("Wrong parameter: %s", chatId);
        }
        return chatJsonTokenizer.deleteExists(willRemoved, current);
    }
}
