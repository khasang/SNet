package io.khasang.snet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carpet {

    @Id
    private int id;
    private String description;

    public Carpet() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
