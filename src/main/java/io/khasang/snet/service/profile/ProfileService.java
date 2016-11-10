package io.khasang.snet.service.profile;

import io.khasang.snet.dao.profile.ProfileDAO;
import io.khasang.snet.entity.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {

    private ProfileDAO profileDAO;

    @Autowired
    public ProfileService (ProfileDAO profileDAO){
        this.profileDAO = profileDAO;
    }

    public void addProfile (Profile profile) {
        profileDAO.addProfile(profile);
    }

    public void updateProfile(Profile profile){
        profileDAO.updateProfile(profile);
    }

    public void updateAvatar(String avatarName, String userLogin ){
        profileDAO.updateAvatar(avatarName, userLogin);
    }

    public Profile getProfileByUserLogin(String userLogin){
       return profileDAO.getProfileByUserLogin(userLogin);
    }

}
