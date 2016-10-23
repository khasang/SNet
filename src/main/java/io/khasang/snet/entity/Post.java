package io.khasang.snet.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "posts")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "post_date")
    private Date dumpDate;

    @Column (name = "post_title")
    private String title;

    @Column (name = "post_contex", length = 1255)
    private String context;

    public Post(){
        dumpDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDumpDate() {
        return dumpDate;
    }

    public void setDumpDate(Date dumpDate) {
        this.dumpDate = dumpDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
