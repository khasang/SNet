package io.khasang.snet.controller;

import io.khasang.snet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan("io.khasang.snet.model.By")
public class AppController {
    @Autowired
    Hello hello;
    @Autowired
    By by;
    @Autowired
    CompanyCrud companyCrud;


    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", hello.getHelloMessage());
        model.addAttribute("by", by.getByMessage());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTableCompany(Model model) {
        model.addAttribute("company", companyCrud.tableCreation());
        return "company";
    }

    @RequestMapping("/insert")
    public String insertToTableCompany(Model model) {
        model.addAttribute("company", companyCrud.insert());
        return "company";
    }

    @RequestMapping("/truncate")
    public String truncateTableCompany(Model model) {
        model.addAttribute("company", companyCrud.truncate());
        return "company";
    }

    @RequestMapping("/select")
    public String selectAllTableCompany(Model model) {
        model.addAttribute("company", companyCrud.selectAll());
        return "company";
    }
}
