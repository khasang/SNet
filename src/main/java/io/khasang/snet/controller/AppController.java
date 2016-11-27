package io.khasang.snet.controller;

import io.khasang.snet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@ComponentScan("io.khasang.snet.model.By")
public class AppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    SelectFromTable selectFromTable;
    @Autowired
    TruncateTable truncateTable;
 	@Autowired
    BackupBase backupBase;
    @Autowired
    TableConfiguration tableConfiguration;

    @RequestMapping("/")
    public String index(Model model) {
        return "register";
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

    @RequestMapping("/allCompany")
    public String selectAllCompany(Model model) {
        // List<Company> componies = createTable.selectAll();
        //model.addAttribute("allCompany", componies);
        // FIXME: 27.11.16 Пофиксить или удалить эту опцию
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

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
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

    @RequestMapping("/chat")
    public String chats(Model model) {
        return "allChats";
    }

    @RequestMapping("/messages")
    public ModelAndView current(@RequestParam(value = "id") String chat_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages");
        try {
            modelAndView.addObject("chat_id", Integer.parseInt(chat_id));
            return modelAndView;
        } catch (NumberFormatException exc) {
            String errorMsg = String.format("Parameter 'id' must be number, '%s' - not number", chat_id);
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg",errorMsg);
            return modelAndView;
        }
    }

    // Bootstrap Examples
    @RequestMapping("/buttons")
    public String buttons(Model model){return "buttons";}

    @RequestMapping("/forms")
    public String forms(Model model){return "forms";}

    @RequestMapping("/tables")
    public String tables(Model model){return "tables";}

    @RequestMapping("/about")
    public String about(Model model){return "about";}

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping("/select")
    public String selectFromTable(Model model){
        model.addAttribute("select", selectFromTable.selectRowsFromTableInArray() );
        return "select";
    }
}
