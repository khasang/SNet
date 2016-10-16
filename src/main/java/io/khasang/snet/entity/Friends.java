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

    private long idInCategory;

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

    public long getIdInCategory() {
        return idInCategory;
    }

    public void setIdInCategory(long idInCategory) {
        this.idInCategory = idInCategory;
    }
}
