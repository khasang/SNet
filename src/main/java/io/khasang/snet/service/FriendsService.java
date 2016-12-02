package io.khasang.snet.service;


import io.khasang.snet.repository.FriendsDAO;
import io.khasang.snet.repository.profile.ProfileDAO;
import io.khasang.snet.repository.userauth.UserDAO;
import io.khasang.snet.entity.Friends;
import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.entity.userauth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FriendsService {

    private FriendsDAO friendsDAO;
    private UserDAO userDAO;
    private ProfileDAO profileDAO;

    @Autowired
    public FriendsService(FriendsDAO friendsDAO, UserDAO userDAO, ProfileDAO profileDAO) {
        this.friendsDAO = friendsDAO;
        this.userDAO = userDAO;
        this.profileDAO = profileDAO;
    }

    // add invite to friends
    public void addInvite(String login, String friend){
        Friends friends = new Friends();
        friends.setIdInUsers(userDAO.getUserByName(login).getID());
        friends.setIdInFriends(userDAO.getUserByName(friend).getID());
        friendsDAO.saveFriend(friends);
    }

    public void approveInvite(String login, String friend){
        Friends friendLine= friendsDAO.getInviteLine(userDAO.getUserByName(friend).getID(),userDAO.getUserByName(login).getID());
        friendLine.setApproved(true);
        friendsDAO.updateFriend(friendLine);
        // for friend double
        Friends newFriend = new Friends();
        newFriend.setIdInUsers(friendLine.getIdInFriends());
        newFriend.setIdInFriends(friendLine.getIdInUsers());
        newFriend.setApproved(true);
        friendsDAO.saveFriend(newFriend);
    }

    public List<Profile> getFriendsList(String login){
        User currentUser=userDAO.getUserByName(login);
        List<Friends> friendsList = friendsDAO.getFriendsList(currentUser.getID());
        List<User> userFriendsList= new ArrayList<>();

        for (Friends fr: friendsList) {
            userFriendsList.add(userDAO.getUserById((int) fr.getIdInFriends()));
        }

        List<Profile> profileFriendsList = new ArrayList<>();

        for (User us: userFriendsList) {
            profileFriendsList.add(profileDAO.getProfileByUserLogin(us.getLogin()));
        }
        return profileFriendsList;
    }

    public List<Profile> getInviteList(String login){
        User currentUser=userDAO.getUserByName(login);
        List<Friends> friendsList = friendsDAO.getInviteList(currentUser.getID());
        return makeProfileList(friendsList);
    }

    public List<Profile> getSendedInvites(String login){
        User currentUser=userDAO.getUserByName(login);
        List<Friends> inviteList = friendsDAO.getSendedInviteList(currentUser.getID());
        List<User> userFriendsList= new ArrayList<>();

        for (Friends fr: inviteList) {
            userFriendsList.add(userDAO.getUserById((int) fr.getIdInFriends()));
        }

        List<Profile> profileFriendsList = new ArrayList<>();

        for (User us: userFriendsList) {
            profileFriendsList.add(profileDAO.getProfileByUserLogin(us.getLogin()));
        }
        return profileFriendsList;

    }

    private List<Profile> makeProfileList(List<Friends> friendsList ){
        List<User> userFriendsList= new ArrayList<>();

        for (Friends fr: friendsList) {
            userFriendsList.add(userDAO.getUserById((int) fr.getIdInUsers()));
        }

        List<Profile> profileFriendsList = new ArrayList<>();

        for (User us: userFriendsList) {
            profileFriendsList.add(profileDAO.getProfileByUserLogin(us.getLogin()));
        }
        return profileFriendsList;
    }

}
