package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.service.profile.ProfileService;
import io.khasang.snet.service.userauth.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @RequestMapping( value = "/profile", method = RequestMethod.GET)
    @Transactional
    public String getProfile(Principal principal, Model model){
        try {
            final String name = principal.getName();
            Profile profile = profileService.getProfileByUserLogin(name);
            String avatar = "anonim.png";
            if ( profile == null){
                profile = new Profile();
                profile.setLogin(name);
                profile.setAvatar(avatar);
                profileService.addProfile(profile);
            }
            model.addAttribute("profile",profile);
            model.addAttribute("user", userService.getUserByLogin(name));
            return "profile";
        }
        catch (Exception e){
            return "Error get Profile: " + e.getMessage();
        }

    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String updateProfile(Principal principal, Model model){
        try {
            final String name = principal.getName();
            String avatar = "anonim.png";
            Profile profile = profileService.getProfileByUserLogin(name);
            if ( profile == null){
                profile = new Profile();
                profile.setAvatar(avatar);
                profile.setLogin(name);
                profileService.addProfile(profile);
            }
            model.addAttribute("profile",profile);

            return "editProfile";
        }
        catch (Exception e){
            return "Error updating Profile: " + e.getMessage();
        }

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("profile") Profile profile, Model model){
        profileService.updateProfile(profile);
        return "editProfile";
    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Principal principal) {
   final String name = principal.getName();
        if (!file.isEmpty()) {
            try {
                byte[] fileBytes = file.getBytes();

                String rootPath = "C:\\proj\\java\\images\\avatars";
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());
                String filename = file.getOriginalFilename();
                filename = filename.substring(filename.lastIndexOf("."), filename.length());
                Profile myPr = profileService.getProfileByUserLogin(name);
                myPr.setAvatar(name+filename);
                profileService.updateProfile(myPr);
                File newFile = new File(rootPath + File.separator +  name + filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();

                System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                return "redirect:/profile";

            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/profile";
            }
        } else {
            return "redirect:/profile";
        }
    }
}


