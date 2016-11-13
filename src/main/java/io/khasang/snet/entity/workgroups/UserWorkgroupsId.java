package io.khasang.snet.entity.workgroups;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserWorkgroupsId implements Serializable {

    @Column(name="workgroupId")
    private long  workgroupId;

    @Column(name="userId")
    private long userId;

    public long getWorkgroupId() {
        return workgroupId;
    }

    public void setWorkgroupId(long workgroupId) {
        this.workgroupId = workgroupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
