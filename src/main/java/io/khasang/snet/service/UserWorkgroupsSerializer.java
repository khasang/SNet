package io.khasang.snet.service;

import io.khasang.snet.entity.workgroups.UserWorkgroups;
import org.springframework.stereotype.Component;

@Component
public class UserWorkgroupsSerializer extends JsonSerializer<UserWorkgroups>  {
    public UserWorkgroupsSerializer() {
        super();
    }
}
