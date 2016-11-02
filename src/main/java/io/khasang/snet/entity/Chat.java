package io.khasang.snet.entity;


import javax.persistence.*;

/* Entity of chat linked with @io.khasang.snet.entity.ChatRegistryUnit */
@Entity(name = "chat")
public class Chat implements AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id")
    private long id;

    @Column(name = "chat_desc")
    private String description;

    public Chat() {
    }

    public Chat(String description) {
        this.description = description;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != chat.id) return false;
        return description != null ? description.equals(chat.description) : chat.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("#%d: Description: %s",id,description);
    }
}
