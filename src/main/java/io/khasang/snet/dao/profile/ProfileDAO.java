package io.khasang.snet.dao.profile;

import io.khasang.snet.entity.profile.Profile;

public interface ProfileDAO {

    void addProfile(Profile profile);
    void updateProfile(Profile profile);
    Profile getProfileByUserLogin (String userLogin);

}
