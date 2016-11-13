package io.khasang.snet.controller.workgroups;


import io.khasang.snet.service.workgroups.WorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkgroupController {

    @Autowired
    WorkgroupService workgroupService;

    @RequestMapping(value = "/allWorkgroups", method = RequestMethod.GET)
    public ModelAndView manageWorkgroups() {
        return new ModelAndView("allWorkgroups","workgroups",workgroupService.getAllWorkgroupList());
    }

    @RequestMapping (value = "/workgroup", method = RequestMethod.GET)
    public ModelAndView workgroup(@RequestParam("id") long id){
        return new ModelAndView("workgroup","workgroup",workgroupService.getWorkgroupById(id));
    }
}
