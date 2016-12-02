package io.khasang.snet.controller.workgroups;

import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.entity.workgroups.UserWorkgroups;
import io.khasang.snet.service.JsonSerializer;
import io.khasang.snet.service.userauth.UserService;
import io.khasang.snet.service.workgroups.UserWorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WorkgroupRestController {

    private UserWorkgroupService userWorkgroupService;
    private JsonSerializer<User> userListJsonSerializer;
    private JsonSerializer<UserWorkgroups>  userWorkgroupsJsonSerializer;
    private UserService userService;

    @Autowired
    public WorkgroupRestController(UserWorkgroupService userWorkgroupService, JsonSerializer<User> userListJsonSerializer, JsonSerializer<UserWorkgroups> userWorkgroupsJsonSerializer, UserService userService) {
        this.userWorkgroupService = userWorkgroupService;
        this.userListJsonSerializer = userListJsonSerializer;
        this.userWorkgroupsJsonSerializer = userWorkgroupsJsonSerializer;
        this.userService = userService;
    }

    @RequestMapping(value = "members/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getMembersList(@PathVariable(value = "id") Long workGroupId) {
        List<Long> membersIdList=userWorkgroupService.getWorkgroupMembersList(workGroupId);
        return userListJsonSerializer.parseToJson(userService.getUsersByIdList(membersIdList));
    }

    @RequestMapping(value = "notMembers/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getNotMembersList(@PathVariable(value = "id") Long workGroupId) {
        List<Long> membersIdList=userWorkgroupService.getWorkgroupMembersList(workGroupId);
        return userListJsonSerializer.parseToJson(userService.getUsersNotInIdList(membersIdList));
    }

    @RequestMapping(value = "members/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addNewMessage(@RequestBody String json) {
        UserWorkgroups userWorkgroups = userWorkgroupsJsonSerializer.parseToEntity(json,UserWorkgroups.class);
        userWorkgroupService.addUserToWorkgroup(userWorkgroups);
        return null;
    }
}
