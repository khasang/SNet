package io.khasang.snet.controller;

import io.khasang.snet.model.By;
import io.khasang.snet.model.CreateTable;
import io.khasang.snet.model.Hello;
import io.khasang.snet.model.SelectFromTable;
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
    CreateTable createTable;
    @Autowired
    SelectFromTable selectFromTable;

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
    @RequestMapping("/select")
    public String selectFromTable(Model model){
        model.addAttribute("select", selectFromTable.selectRowsFromTableInArray() );
        return "select";
    }
}
