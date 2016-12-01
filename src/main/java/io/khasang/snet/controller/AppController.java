package io.khasang.snet.controller;

import io.khasang.snet.service.UsersPasswordChanger;
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
public class AppController {
    
    @RequestMapping("/")
    public String index(Model model) {
        return "register";
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
}
