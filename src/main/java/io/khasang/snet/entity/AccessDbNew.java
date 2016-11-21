package io.khasang.snet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessDbNew {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public AccessDbNew() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
