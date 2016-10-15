package io.khasang.snet.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carpet {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    public Carpet() {
    }
}
