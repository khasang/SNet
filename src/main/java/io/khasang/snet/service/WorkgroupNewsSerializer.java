package io.khasang.snet.service;


import io.khasang.snet.entity.workgroups.WorkgroupNews;
import org.springframework.stereotype.Component;

@Component
public class WorkgroupNewsSerializer extends JsonSerializer<WorkgroupNews> {

    public WorkgroupNewsSerializer() {
        super();
    }
}

