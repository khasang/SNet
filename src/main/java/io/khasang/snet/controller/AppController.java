package io.khasang.snet.controller;

import io.khasang.snet.model.*;
import io.khasang.snet.service.UsersPasswordChanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    UsersPasswordChanger usersPasswordChanger;
    
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
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
    public String deleteTableCompany(Model model) {
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
    public String createTableEmployeesAndCities(Model model) {
        model.addAttribute("createEmployees", tableConfiguration.employeesTableCreation());
        model.addAttribute("createCities", tableConfiguration.citiesTableCreation());
        return "createEmployees";
    }

    @RequestMapping("/insertEmployees")
    public String insertInTableEmployeesAndCities(Model model) {
        model.addAttribute("insertEmployees", tableConfiguration.insertEmployees());
        model.addAttribute("insertCities", tableConfiguration.insertCities());
        return "insertEmployees";
    }

    @RequestMapping("/selectEmployees")
    public String selectEmployeesByCountry(Model model) {
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

    // add login page for Spring Security
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/posts")
    public String posts() {
        return "posts";
    }

    @RequestMapping("/backup")
    public String makeBasebackUp(Model model) {
        model.addAttribute("backUpMessage", backupBase.makeBackUp());
        return "backUp";
    }

    @RequestMapping("/confidential/page")
    public String secureTable(Model model) {
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

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping("/endRegistration")
    public String ednRegistration(Model model) {
        return "endRegistration";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    // Bootstrap Examples
    @RequestMapping("/buttons")
    public String buttons(Model model){return "buttons";}

    @RequestMapping("/forms")
    public String forms(Model model){return "forms";}

    @RequestMapping("/tables")
    public String tables(Model model){return "tables";}

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

}
