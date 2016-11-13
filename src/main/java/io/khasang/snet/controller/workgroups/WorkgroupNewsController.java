package io.khasang.snet.controller.workgroups;

import io.khasang.snet.service.workgroups.WorkgroupNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class WorkgroupNewsController {

    @Autowired
    WorkgroupNewsService workgroupNewsService;
    
    @RequestMapping(value = "/workgroupNewsAll", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getMessagesList(@RequestBody Long workGroupId) {
        return workgroupNewsService.getAllWorkgroupNewsList(workGroupId);
    }

}
