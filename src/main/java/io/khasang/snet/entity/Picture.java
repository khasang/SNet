package io.khasang.snet.entity;

import javax.persistence.*;
import java.awt.image.BufferedImage;

@Entity(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "describ")
    private String description;

    @Column(name = "body")
    private byte[] imageBody;

    @Column(name = "owner_id")
    private long ownerId;

    public Picture() {
    }

    public Picture(String description, byte[] imageBody, long ownerId) {
        this.description = description;
        this.imageBody = imageBody;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageBody() {
        return this.imageBody;
    }

    public void setImageBody(byte[] imageBody) {
        this.imageBody = imageBody;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
