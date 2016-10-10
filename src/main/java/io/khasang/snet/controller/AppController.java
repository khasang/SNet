package io.khasang.snet.controller;

import io.khasang.snet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    DeleteTable deleteTable;

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

    @RequestMapping("/insert")
    public String insertInTableCompany(Model model) {
        model.addAttribute("create", createTable.insert());
        return "create";
    }

    @RequestMapping("/delete")
    public String deleteTableCompany(Model model){
        model.addAttribute("create", deleteTable.delete());
        return "create";
    }

    @RequestMapping("/delete/{companyId}")
    public String deleteRecord(@PathVariable int companyId, Model model) {
        model.addAttribute("delete", deleteTable.deleteRecord(companyId));
        return "deleteID";
    }

    @RequestMapping("/allCompany")
    public String selectAllCompany(Model model) {
        List<Company> componies = createTable.selectAll();
        model.addAttribute("allCompany", componies);
        return "allCompany";
    }
}
