package io.khasang.snet.dao.workgroups;

import io.khasang.snet.entity.workgroups.Workgroup;

import java.util.List;

public interface WorkgroupDAO {

    void addWorkgroup(Workgroup workgroup);

    void updateWorgroup(Workgroup workgroup);

    void deleteWorkgroup(Workgroup workgroup);

    Workgroup getWorkgroupById(long workgroupId);

    List<Workgroup> getAllWorkgroupList ();

    List<Workgroup> getWorkgroupList (Long[] workgroupIdList);

}
