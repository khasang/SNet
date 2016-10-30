package io.khasang.snet.controller;

import io.khasang.snet.model.*;
import io.khasang.snet.service.TableCreator;
import io.khasang.snet.service.UsersPasswordChanger;
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
    @Autowired
    TruncateTable truncateTable;
 	@Autowired
    BackupBase backupBase;
    @Autowired
    TableConfiguration tableConfiguration;

    @Autowired
    TableCreator weatherTableCreator;

    @Autowired
    UsersPasswordChanger usersPasswordChanger;

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", hello.getHelloMessage());
        model.addAttribute("by", by.getByMessage());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTableCompany(Model model) {
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

    @RequestMapping("/createEmployees")
    public String createTableEmployeesAndCities(Model model){
        model.addAttribute("createEmployees", tableConfiguration.employeesTableCreation());
        model.addAttribute("createCities",tableConfiguration.citiesTableCreation());
        return "createEmployees";
    }

    @RequestMapping("/insertEmployees")
    public String insertInTableEmployeesAndCities(Model model){
        model.addAttribute("insertEmployees", tableConfiguration.insertEmployees());
        model.addAttribute("insertCities",tableConfiguration.insertCities());
        return "insertEmployees";
    }

    @RequestMapping("/selectEmployees")
    public String selectEmployeesByCountry(Model model){
        model.addAttribute("selectEmployees", tableConfiguration.selectEmployeesByCountry("Англия"));
        return "selectEmployees";
    }


    @RequestMapping("/truncate")
    public String truncateTable(Model model) {
        model.addAttribute("truncate", truncateTable.truncate());
        return "truncate";
    }

    @RequestMapping("/rest")
    public String rest() {
        return "rest";
    }

    @RequestMapping("/posts")
    public String posts() {
        return "posts";
    }

    @RequestMapping("/employees")
    public String employees() {
        return "employees";
    }

    @RequestMapping("/backup")
    public String makeBasebackUp(Model model){
        model.addAttribute("backUpMessage",backupBase.makeBackUp());
        return "backUp";
    }

    @RequestMapping("/confidential/page")
    public String secureTable(Model model){
        model.addAttribute("secure", "This is a very secure place");
        return "secure";
    }

    /* Weather table creation request */
    @RequestMapping("/confidential/create/weather")
    public String createTableWeather(Model model) {
        model.addAttribute("create", weatherTableCreator.dropAndCreate());
        return "create";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;

    }

    /* @param login: user's login from database
    *  @param newPassword new password of user, will be automatically encoded
    *  Request example:
    *  http://localhost:8080/confidential/changepwd?login=user_login&newPwd=new_password
    *  were, login = @param login newPwd = @param newPassword
    *  WARNING: take parameters without any quotes or apostrophe
    *  */
    @RequestMapping("/confidential/changepwd")
    public ModelAndView changepwd(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "newPwd") String newPassword) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("change");
        String response = usersPasswordChanger.change(login, new BCryptPasswordEncoder().encode(newPassword));
        modelAndView.addObject("response", response);
        return modelAndView;
    }

    @RequestMapping("/users/register")
    public String register(Model model) {
       return "register";
    }
}
