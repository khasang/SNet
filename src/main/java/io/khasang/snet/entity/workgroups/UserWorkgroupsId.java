package io.khasang.snet.entity.workgroups;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserWorkgroupsId implements Serializable {

    @Column(name="workgroupId")
    private String workgroupId;

    @Column(name="userId")
    private String userId;

}
