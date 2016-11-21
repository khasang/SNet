package io.khasang.snet.controller.workgroups;


import io.khasang.snet.entity.workgroups.WorkgroupNews;
import io.khasang.snet.service.JsonSerializer;
import io.khasang.snet.service.userauth.UserService;
import io.khasang.snet.service.workgroups.WorkgroupNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/news")
public class WorkgroupNewsController {

    @Autowired
    WorkgroupNewsService workgroupNewsService;

    @Autowired
    UserService userService;

    @Autowired
    private JsonSerializer<WorkgroupNews> userWorkgroupsNewsJsonSerializer;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getMessagesList(@PathVariable(value = "id") Long workGroupId) {
        return workgroupNewsService.getAllWorkgroupNewsList(workGroupId);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addNewMessage(@RequestBody String json) {
        WorkgroupNews userWorkgroups = userWorkgroupsNewsJsonSerializer.parseToEntity(json, WorkgroupNews.class);
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        userWorkgroups.setNewsDate(date.format(new Date()));
        workgroupNewsService.addWorkgroupNews(userWorkgroups);
        return "Новость успешно добавлена";
    }

}
