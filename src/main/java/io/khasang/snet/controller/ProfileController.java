package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/use_profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;



    @RequestMapping(value = "/my",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public Object getProfile(Principal principal){
        try {
            final String name = principal.getName();
            Profile profile = profileService.getProfileByUserLogin(name);
            if ( profile == null){
                profile = new Profile();
                profile.setLogin(name);
                profileService.addProfile(profile);
            }
            return profile;
        }
        catch (Exception e){
            return "Error get Profile: " + e.getMessage();
        }

    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object updateProfile(@RequestBody Profile profile, HttpServletResponse response){
        try {
            profileService.updateProfile(profile);
            return profile;
        }
        catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating Profile: " + e.getMessage();
        }

    }

}
