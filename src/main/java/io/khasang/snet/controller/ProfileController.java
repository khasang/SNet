package io.khasang.snet.controller;

import io.khasang.snet.entity.profile.Profile;
import io.khasang.snet.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Principal principal) {
   final String name = principal.getName();
        if (!file.isEmpty()) {
            try {
                byte[] fileBytes = file.getBytes();

                String rootPath = request.getSession().
                        getServletContext().getRealPath("/WEB-INF/views/images/avatars");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());

                String filename = file.getOriginalFilename();
                filename = filename.substring(filename.lastIndexOf("."), filename.length());

                File newFile = new File(rootPath + File.separator +  name + filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();

                System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                return "File is saved under: " + rootPath + File.separator + name + filename;

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }
}


