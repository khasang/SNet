package io.khasang.snet.repository.workgroups;

import io.khasang.snet.entity.workgroups.Workgroup;

import java.util.List;

public interface WorkgroupDAO {

    void addWorkgroup(Workgroup workgroup);
    void updateWorkgroup(Workgroup workgroup);
    void deleteWorkgroup(Workgroup workgroup);
    Workgroup getWorkgroupById(long workgroupId);
    List<Workgroup> getAllWorkgroupList ();
    List<Workgroup> getWorkgroupListByIdList(List<Long> workgroupIdList);
    List<Workgroup> getAllDepartments ();
    List<Workgroup> getDependentUnits(long workgroupId);
    List<Workgroup> getDependentGroups (long workgroupId);

}
