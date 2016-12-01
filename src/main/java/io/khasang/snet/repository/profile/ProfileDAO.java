package io.khasang.snet.repository.profile;

import io.khasang.snet.entity.profile.Profile;

public interface ProfileDAO {

    void addProfile(Profile profile);
    void updateProfile(Profile profile);
    void updateAvatar(String avatarName, String userLogin);
    Profile getProfileByUserLogin (String userLogin);

}
