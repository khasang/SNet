package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.service.profile.ProfileService;
import io.khasang.snet.service.userauth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @RequestMapping( value = "/profile", method = RequestMethod.GET)
    @Transactional
    public String getProfile(Principal principal, Model model){
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        try {
            final String name = principal.getName();
            Profile profile = profileService.getProfileByUserLogin(name);
            String avatar = "anonim.jpg";
            if ( profile == null){
                profile = new Profile();
                profile.setLogin(name);
                profile.setAvatar(avatar);
                profile.setCreated(date.format(new Date()));
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

    @RequestMapping( value = "/user")
    public String getUserProfile(@RequestParam(value = "userLogin") String userLogin, Model model){
        try {
            Profile profile = profileService.getProfileByUserLogin(userLogin);
            model.addAttribute("profile",profile);
            model.addAttribute("user", userService.getUserByLogin(userLogin));
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
            Profile profile = profileService.getProfileByUserLogin(name);
            if ( profile == null){
               return "redirect:/profile";
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
        @ResponseBody
        public String handleFileUpload(@RequestParam("uploadfile") MultipartFile file) {
            final String name =  SecurityContextHolder.getContext().getAuthentication().getName();
            if (!file.isEmpty()) {
                try {
                    byte[] fileBytes = file.getBytes();

                    if(!(file.getContentType().toLowerCase().equals("image/jpg")
                            || file.getContentType().toLowerCase().equals("image/jpeg")
                            || file.getContentType().toLowerCase().equals("image/png"))){
                        return " only jpg/png file types are supported";
                    }

                    String rootPath = "C:\\proj\\java\\images\\avatars";
                    System.out.println("Server rootPath: " + rootPath);
                    System.out.println("File original name: " + file.getOriginalFilename());
                    System.out.println("File content type: " + file.getContentType());
                    System.out.println(name);
                    String filename = file.getOriginalFilename();
                    filename = filename.substring(filename.lastIndexOf("."), filename.length());

                    Profile myPr = profileService.getProfileByUserLogin(name);
                    myPr.setAvatar(name + filename);
                    profileService.updateProfile(myPr);
                    File newFile = new File(rootPath + File.separator + name + filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                    stream.write(fileBytes);
                    stream.close();
                    System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                    return "Foto upload.";

                } catch (Exception e) {
                    e.printStackTrace();
                    return "The selected file does not meet the requirements.";
                }
            } else {
                return "Select the file.";
            }
        }

}


