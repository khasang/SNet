package io.khasang.snet.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Entity that represents message
 */
@Entity(name = "message")
public class Message implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sender_id")
    private long sender;

    @Column(name = "receiver_id")
    private long receiver;

    @Column(name = "message_body")
    @Type(type = "org.hibernate.type.TextType")
    private String body;

    @Column(name = "send_time")
    @Type(type = "org.hibernate.type.CalendarType")
    private Calendar stamp;

    public Message() {
    }

    public Message(long sender, long receiver, String body, Calendar stamp) {
        this.sender = sender;
        this.receiver = receiver;
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

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
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
        if (receiver != message.receiver) return false;
        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        return stamp != null ? stamp.equals(message.stamp) : message.stamp == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (sender ^ (sender >>> 32));
        result = 31 * result + (int) (receiver ^ (receiver >>> 32));
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (stamp != null ? stamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "Message: %1$d Sender: %2$d Receiver: %3$d Body length: %4$d Timestamp: %5$td.%5$tm.%5$ty %5$tH:%5$tM:%5$tS",
                id, sender, receiver, body.length(),stamp.getTime());
    }
}
