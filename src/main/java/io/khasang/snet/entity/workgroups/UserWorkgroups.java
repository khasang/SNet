package io.khasang.snet.entity.workgroups;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserWorkgroups {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long workgroupId;

    private long userId;
    // Позволяет добавлять новости в группу
    private boolean isAdmin;
}
