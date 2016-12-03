package io.khasang.snet.service;

import io.khasang.snet.entity.profile.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileJsonSerializer extends JsonSerializer<Profile> {
    public ProfileJsonSerializer() {
        super();
    }
}