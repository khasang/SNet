package io.khasang.snet.controller.workgroups;

import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.service.JsonSerializer;
import io.khasang.snet.service.userauth.UserService;
import io.khasang.snet.service.workgroups.UserWorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class WorkgroupRestController {

    @Autowired
    UserWorkgroupService userWorkgroupService;


    @Autowired
    private JsonSerializer<User> userListJsonSerializer;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getMembersList(@PathVariable(value = "id") Long workGroupId) {
        List<Long> membersIdList=userWorkgroupService.getWorkgroupMembersList(workGroupId);
        return userListJsonSerializer.parseToJson(userService.getUsersByIdList(membersIdList));
    }

}
