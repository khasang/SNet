package io.khasang.snet.entity;

import javax.persistence.*;
import java.util.Arrays;

/* This is the entity of picture */
@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* Description of image */
    @Column(name = "describ")
    private String description;

    /* Body of the image */
    @Column(name = "body")
    private byte[] imageBody;

    /* Id of owner, in users table */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (id != picture.id) return false;
        if (ownerId != picture.ownerId) return false;
        if (description != null ? !description.equals(picture.description) : picture.description != null) return false;
        return Arrays.equals(imageBody, picture.imageBody);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(imageBody);
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%d: '%s' owner: %d size: ",id,description,ownerId));

        if (imageBody.length < 1024) {
            result.append(String.format("%d bytes", imageBody.length));
        } else if (imageBody.length < (1024*1024)) {
            result.append(String.format("%d Kb", imageBody.length/1024));
        } else if (imageBody.length < (1024*1024*1024)) {
            result.append(String.format("%d Mb", imageBody.length/(1024*1024)));
        } else result.append("not recognized");

        return result.toString();
    }
}
