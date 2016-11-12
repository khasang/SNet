package io.khasang.snet.entity.workgroups;

import javax.persistence.*;

@Entity
@Table(name = "userWorkgroups")
public class UserWorkgroups {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @EmbeddedId
    UserWorkgroupsId prKey;

    // Позволяет добавлять новости в группу
    private boolean isAdmin;
}
