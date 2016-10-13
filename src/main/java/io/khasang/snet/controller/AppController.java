package io.khasang.snet.controller;

import io.khasang.snet.model.By;
import io.khasang.snet.model.CreateTable;
import io.khasang.snet.model.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("io.khasang.snet.model.By")
public class AppController {
    @Autowired
    Hello hello;
    @Autowired
    By by;
    @Autowired
    CreateTable createTable;

    @Autowired
    UsersPasswordChanger usersPasswordChanger;

    @RequestMapping("/")
    public String hello(Model model){
        model.addAttribute("hello", hello.getHelloMessage());
        model.addAttribute("by", by.getByMessage());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTableCompany(Model model){
        model.addAttribute("create", createTable.tableCreation());
        return "create";
    }

    @RequestMapping("/confidential/page")
    public String secureTable(Model model){
        model.addAttribute("secure", "This is a very secure place");
        return "secure";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping("/changepwd")
    public ModelAndView changepwd(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "newPwd") String newPassword) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change");
        String response = usersPasswordChanger.change(login, new BCryptPasswordEncoder().encode(newPassword));
        modelAndView.addObject("response", response);
        return modelAndView;
    }
}
