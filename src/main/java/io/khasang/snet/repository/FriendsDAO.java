package io.khasang.snet.repository;

import io.khasang.snet.entity.Friends;

import java.util.List;

public interface FriendsDAO {
    // adding Invite to friends (approve = false)
    void saveFriend(Friends friend);
    // set approve to true in invite
    void updateFriend(Friends friend);
    List<Friends> getFriendsList(long id);
    // get all friends from list where approve - false
    List<Friends> getInviteList(long id);
    // get all my invites to friend
    List<Friends> getSendedInviteList(long id);
    // get line from database
    Friends getFriendLine(long id);
    Friends getInviteLine(long user,long friend);
}
