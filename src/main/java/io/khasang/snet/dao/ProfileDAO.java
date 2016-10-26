package io.khasang.snet.dao;

import io.khasang.snet.entity.Profile;

public interface ProfileDAO {
    
    public void fillProfile(Profile Profile);

    public void updateProfile(Profile profile);

    public Profile getProfileByUserId(Long UserId);

}
