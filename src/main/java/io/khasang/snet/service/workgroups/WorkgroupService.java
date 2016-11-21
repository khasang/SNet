package io.khasang.snet.service.workgroups;

import io.khasang.snet.dao.workgroups.WorkgroupDAO;
import io.khasang.snet.entity.workgroups.Workgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkgroupService {

    @Autowired
    WorkgroupDAO workgroupDAO;

    public void addWorkgroup(Workgroup workgroup){
        workgroupDAO.addWorkgroup(workgroup);
    }

    public void updateWorkgroup(Workgroup workgroup){
        workgroupDAO.updateWorkgroup(workgroup);
    }

    public void deleteWorkgroup(Workgroup workgroup){
        workgroupDAO.deleteWorkgroup(workgroup);
    }

    public Workgroup getWorkgroupById(long workgroupId){
        return workgroupDAO.getWorkgroupById(workgroupId);
    }

    public List<Workgroup> getWorkgroupListByIdList(List<Long> workgroupIdList){
        return workgroupDAO.getWorkgroupListByIdList(workgroupIdList);
    }

    public List<Workgroup> getAllWorkgroupList(){
        return workgroupDAO.getAllWorkgroupList();
    }

    public List<Workgroup> getAllDepartments() {
        return workgroupDAO.getAllDepartments();
    }

    public List<Workgroup> getDependentUnits(long workgroupId) {
        return workgroupDAO.getDependentUnits(workgroupId);
    }

    public List<Workgroup> getDependentGroups(long workgroupId) {
        return workgroupDAO.getDependentGroups(workgroupId);
    }
}
