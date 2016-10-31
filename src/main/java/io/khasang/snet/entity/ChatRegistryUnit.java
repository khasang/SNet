package io.khasang.snet.entity;

import io.khasang.snet.entity.userauth.User;

import javax.persistence.*;

@Entity(name = "chat_registry")
public class ChatRegistryUnit implements AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registry_unit_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public ChatRegistryUnit() {
    }

    public ChatRegistryUnit(Chat chat, User user) {
        this.chat = chat;
        this.user = user;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatRegistryUnit unit = (ChatRegistryUnit) o;

        if (id != unit.id) return false;
        if (chat != null ? !chat.equals(unit.chat) : unit.chat != null) return false;
        return user != null ? user.equals(unit.user) : unit.user == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("#%d User: #%d, Chat: %s",id,user.getID(),chat.toString());
    }
}
