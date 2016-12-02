package io.khasang.snet.controller.workgroups;

import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupType;
import io.khasang.snet.service.workgroups.WorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkgroupController {

    private WorkgroupService workgroupService;

    @Autowired
    public WorkgroupController(WorkgroupService workgroupService) {
        this.workgroupService = workgroupService;
    }

    @RequestMapping(value = "/allWorkgroups", method = RequestMethod.GET)
    public ModelAndView manageWorkgroups() {
        List<WorkgroupType> wgType = new ArrayList<>();
        wgType.add(WorkgroupType.DEPARTMENT);
        wgType.add(WorkgroupType.UNIT);
        wgType.add(WorkgroupType.GROUP);

        ModelAndView modelAndView = new ModelAndView("allWorkgroups");
        modelAndView.addObject("workgroups",workgroupService.getAllWorkgroupList());
        modelAndView.addObject("types",wgType);
        return modelAndView;
    }

    @RequestMapping(value = "/addNewWorkgroup", method = RequestMethod.POST)
    public ModelAndView addWorkgroup(@ModelAttribute ("workgroup") Workgroup workgroup){
        System.out.println(workgroup);
        workgroupService.addWorkgroup(workgroup);
        String message = "You successfully added a: "+ workgroup.getTitle();
        return new ModelAndView("redirect:/allWorkgroups","addMessage", message) ;
    }
    @RequestMapping (value = "/workgroupDelete", method = RequestMethod.GET)
    public ModelAndView deleteWorkgroup(@RequestParam("id") long id){
        Workgroup workgroup = workgroupService.getWorkgroupById(id);
        boolean hasErrors= false;
        String message = "";

        if (workgroup.getWorkgroupType()==WorkgroupType.DEPARTMENT){
            List<Workgroup> dependendentWg = workgroupService.getDependentUnits(workgroup.getId());
            if (dependendentWg.size()>0){
                hasErrors= true;
                message = "The workgroup "+workgroup.getTitle()+" have a dependent units and can't be deleted!";
            }
        }
        else if(workgroup.getWorkgroupType()==WorkgroupType.UNIT){
            List<Workgroup> dependendentWg = workgroupService.getDependentGroups(workgroup.getId());
            if (dependendentWg.size()>0){
                hasErrors= true;
                message = "The workgroup "+workgroup.getTitle()+" have a dependent groups and can't be deleted!";
            }
        }

        if (hasErrors) {
            return new ModelAndView("redirect:/allWorkgroups","deleteMessage", message) ;
        }
        else {
            workgroupService.deleteWorkgroup(workgroup);
            message = "Workgroup "+workgroup.getTitle()+" successfully deleted";
        }
        return new ModelAndView("redirect:/allWorkgroups","delMessage", message) ;
    }

    @RequestMapping (value = "/workgroup", method = RequestMethod.GET)
    public ModelAndView workgroup(@RequestParam("id") long id){
        return new ModelAndView("workgroup","workgroup",workgroupService.getWorkgroupById(id));
    }
    @RequestMapping (value = "/workgroupEdit", method = RequestMethod.GET)
    public ModelAndView editWorkgroup(@RequestParam("id") long id){

        return new ModelAndView("editWorkgroup","workgroup",workgroupService.getWorkgroupById(id));
    }

    @RequestMapping(value = "/updateWorkgroup/{id}", method = RequestMethod.POST)
    public ModelAndView updateWorkgroup(@PathVariable(value = "id") long id,@ModelAttribute ("workgroup") Workgroup workgroup){
        Workgroup workgroupInBase = workgroupService.getWorkgroupById(id);
        workgroupInBase.setDescription(workgroup.getDescription());
        workgroupInBase.setTitle(workgroup.getTitle());
        workgroupService.updateWorkgroup(workgroupInBase);
        String message = "You successfully update a: "+ workgroupInBase.getTitle();
        return new ModelAndView("redirect:/allWorkgroups","updateMessage", message);
    }
}
