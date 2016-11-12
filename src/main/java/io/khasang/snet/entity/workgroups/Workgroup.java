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
}
