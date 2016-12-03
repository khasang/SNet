package io.khasang.snet.repository.profile;

import io.khasang.snet.entity.profile.Profile;

import java.util.List;

public interface ProfileDAO {

    void addProfile(Profile profile);
    void updateProfile(Profile profile);
    void updateAvatar(String avatarName, String userLogin);
    Profile getProfileByUserLogin (String userLogin);
    List<Profile> getSameProfile  (String userLogin);
    List<Profile> getProfileLike (String userLoginLike, String userLogin);

}
