package io.khasang.snet.controller;

import io.khasang.snet.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ModelAndView friends() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ModelAndView("friends","friends",friendsService.getFriendsList(currentLogin));
    }

    @RequestMapping(value = "/invites", method = RequestMethod.GET)
    public ModelAndView invites() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ModelAndView("invites","friends",friendsService.getInviteList(currentLogin));
    }

    @RequestMapping(value = "/approve")
    public ModelAndView approve(@RequestParam(value = "friend")String friend){
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        friendsService.approveInvite(currentLogin,friend);
        return new ModelAndView("invites","friends",friendsService.getInviteList(currentLogin));
    }
}
