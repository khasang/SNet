package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.FriendsService;
import io.khasang.snet.service.profile.ProfileService;
import io.khasang.snet.service.userauth.UserService;
import io.khasang.snet.util.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

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

    @RequestMapping(value = "/addFriend")
    public String addFriend(@RequestParam(value = "friend")String friend){
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        friendsService.addInvite(currentLogin,friend);
        return "redirect:/searchFriends";
    }

    @RequestMapping(value = "/searchFriends",method = RequestMethod.GET)
    public ModelAndView searchFriends(){
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        // Map for jsp Key - Profile : Value - eNum of FriendStatus
        List<User> allUsers=userService.getAllUsers(currentLogin);
        Map<Profile,String> friends = new HashMap<>();
        List<Profile> friendsList = friendsService.getFriendsList(currentLogin);
        List<Profile> inviteList = friendsService.getInviteList(currentLogin);
        List<Profile> sendedInvites = friendsService.getSendedInvites(currentLogin);

        for (User currUser: allUsers) {
            Profile userProfile = profileService.getProfileByUserLogin(currUser.getLogin());
            if (userProfile !=null) {
                if (searchInList(userProfile,friendsList)) {
                    friends.put(userProfile, FriendStatus.IN_FRIENDS.toString());
                } else if (searchInList(userProfile,inviteList)) {
                    friends.put(userProfile, FriendStatus.INVITE_RECEIVED.toString());
                } else if (searchInList(userProfile,sendedInvites)) {
                    friends.put(userProfile, FriendStatus.INVITE_SENDED.toString());
                } else {
                    friends.put(userProfile, FriendStatus.NOT_A_FRIEND.toString());
                }
            }
        }
        return new ModelAndView("searchFriends","friends",friends);
    }

    private boolean searchInList(Profile toSearch,List<Profile> listToSearch){
        for (Profile profile: listToSearch) {
            if (profile.getLogin().equals(toSearch.getLogin())){
                return true;
            }
        }
        return false;
    }
}
