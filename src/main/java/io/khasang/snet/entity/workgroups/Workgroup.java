package io.khasang.snet.entity.workgroups;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "workgroups")
public class Workgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private WorkgroupType workgroupType;

    private String title;

    private String description;

    private long headWorkgroupId;

    public Workgroup() {
        headWorkgroupId=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WorkgroupType getWorkgroupType() {
        return workgroupType;
    }

    public void setWorkgroupType(WorkgroupType workgroupType) {
        this.workgroupType = workgroupType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getHeadWorkgroupId() {
        return headWorkgroupId;
    }

    public void setHeadWorkgroupId(long headWorkgroupId) {
        this.headWorkgroupId = headWorkgroupId;
    }
}
