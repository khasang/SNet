package io.khasang.snet.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Calendar;

/**
 * Entity that represents message, linked with @io.khasang.snet.entity.Chat
 * by field chat
 */
@Entity(name = "message")
public class Message implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sender_id")
    private long sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "message_body")
    @Type(type = "org.hibernate.type.TextType")
    private String body;

    @Column(name = "send_time")
    @Type(type = "org.hibernate.type.CalendarType")
    private Calendar stamp;

    public Message() {
    }

    public Message(long sender, Chat chat, String body, Calendar stamp) {
        this.sender = sender;
        this.chat = chat;
        this.body = body;
        this.stamp = stamp;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Calendar getStamp() {
        return stamp;
    }

    public void setStamp(Calendar stamp) {
        this.stamp = stamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (sender != message.sender) return false;
        if (chat != null ? !chat.equals(message.chat) : message.chat != null) return false;
        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        return stamp != null ? stamp.equals(message.stamp) : message.stamp == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (sender ^ (sender >>> 32));
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (stamp != null ? stamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "#%1$d Sender: %2$d Chat: %3$s Body length: %4$d Timestamp: %5$td.%5$tm.%5$ty %5$tH:%5$tM:%5$tS",
                id, sender, chat, body.length(),stamp.getTime());
    }
}
