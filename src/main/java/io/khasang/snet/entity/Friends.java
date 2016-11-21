package io.khasang.snet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idInUsers;

    private long idInFriends;

    private boolean approved;

    public Friends() {
        approved=false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdInUsers() {
        return idInUsers;
    }

    public void setIdInUsers(long idInUsers) {
        this.idInUsers = idInUsers;
    }

    public long getIdInFriends() {
        return idInFriends;
    }

    public void setIdInFriends(long idInFriends) {
        this.idInFriends = idInFriends;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
