package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.service.FriendsService;
import io.khasang.snet.service.ProfileJsonSerializer;
import io.khasang.snet.service.profile.ProfileService;
import io.khasang.snet.util.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FriendRestController {

    private FriendsService friendsService;
    private ProfileService profileService;
    private ProfileJsonSerializer profileJsonSerializer;

    @Autowired
    public FriendRestController(FriendsService friendsService, ProfileService profileService,
                                ProfileJsonSerializer profileJsonSerializer) {
        this.friendsService = friendsService;
        this.profileService = profileService;
        this.profileJsonSerializer = profileJsonSerializer;
    }

    @RequestMapping(value = "/searchFriends2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object searchFriends(){
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        // Map for jsp Key - Profile : Value - eNum of FriendStatus
        List<Profile> allUsers=profileService.getSameUsers(currentLogin);
        return getProfiles(currentLogin, allUsers);
    }

    @RequestMapping(value = "/searchFriendsLike", method = RequestMethod.POST)
    public Object searchFriendsLike(@RequestBody  String searchBody) {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Profile> searchUsers = profileService.getProfileLike(searchBody,currentLogin);
        return getProfiles(currentLogin, searchUsers);
    }

    private Object getProfiles(String currentLogin, List <Profile> searchUsers){
        Map<String,String> friends = new HashMap<>();
        List<Profile> friendsList = friendsService.getFriendsList(currentLogin);
        List<Profile> inviteList = friendsService.getInviteList(currentLogin);
        List<Profile> sendedInvites = friendsService.getSendedInvites(currentLogin);
        for (Profile userProfile: searchUsers) {

            if (userProfile !=null) {
                String profileJson = profileJsonSerializer.parseToJson(userProfile);
                if (searchInList(userProfile,friendsList)) {
                    friends.put(profileJson, FriendStatus.IN_FRIENDS.toString());

                }else if (searchInList(userProfile,sendedInvites)) {
                    friends.put(profileJson, FriendStatus.INVITE_SENDED.toString());
                } else if (searchInList(userProfile,inviteList)) {
                    friends.put(profileJson, FriendStatus.INVITE_RECEIVED.toString());
                }  else {
                    friends.put(profileJson, FriendStatus.NOT_A_FRIEND.toString());
                }
            }
        }
        return friends;
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
