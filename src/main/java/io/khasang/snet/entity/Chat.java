package io.khasang.snet.entity;

import javax.persistence.*;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "chat_desc")
    private String chatDesc;

    @Transient
    private int countLow;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChatDesc() {
        return chatDesc;
    }

    public void setChatDesc(String chatDesc) {
        this.chatDesc = chatDesc;
    }
}
