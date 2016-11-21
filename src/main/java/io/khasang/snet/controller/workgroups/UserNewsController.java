package io.khasang.snet.controller.workgroups;

import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupNews;
import io.khasang.snet.service.userauth.UserService;
import io.khasang.snet.service.workgroups.WorkgroupNewsService;
import io.khasang.snet.service.workgroups.WorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class UserNewsController {

    @Autowired
    WorkgroupNewsService workgroupNewsService;

    @Autowired
    UserService userService;

    @Autowired
    WorkgroupService workgroupService;

    @RequestMapping(value = "/userNews", method = RequestMethod.GET)
    public String getUserMessagesList(Principal principal, Model model) {

        try {
            User myUser = userService.getUserByLogin(principal.getName());
            List<WorkgroupNews> userWorkNews = workgroupNewsService.getAllUserWorkgroupNewsList((long) (myUser.getID()));
            List<Workgroup> allGroup = workgroupService.getAllWorkgroupList();
            Map<WorkgroupNews, String> userNewsList = new HashMap<>();
            for (WorkgroupNews news : userWorkNews) {
                for (Workgroup wg : allGroup) {
                    if (news.getWorkgroupId() == wg.getId()) {
                        userNewsList.put(news, wg.getTitle());
                    }
                }

            }
            model.addAttribute("userNews", userNewsList);
            return "userWgNews";
        }
        catch (Exception e){
            return "userWgNews";
        }
    }


    @RequestMapping(value = "/userWorkgroups", method = RequestMethod.GET)
    public String getUserWorkroupList(Principal principal, Model model) {

        try {
            User myUser = userService.getUserByLogin(principal.getName());
            List<Workgroup> userWorkNews = workgroupNewsService.getAllUserWorkgroupList((long) (myUser.getID()));

            model.addAttribute("userWorkgroup", userWorkNews);
            return "userWorkgroups";
        }
        catch (Exception e){
            return "userWorkgroups";
        }
    }

}
