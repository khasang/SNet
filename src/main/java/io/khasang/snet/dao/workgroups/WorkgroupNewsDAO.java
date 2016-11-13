package io.khasang.snet.dao.workgroups;

import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupNews;

import java.util.List;

public interface WorkgroupNewsDAO  {

    void addWorkgroupNews(WorkgroupNews workgroupNews);

    WorkgroupNews getWorkgroupNews(Long id);

    void updateWorkgroup(WorkgroupNews workgroup);

    void deleteWorkgroup(WorkgroupNews workgroup);

    List<WorkgroupNews> getAllWorkgroupNewsList(Workgroup workgroup);

    List<WorkgroupNews> getAllUserWorkgroupNewsList(List<Long> workgroupIdList);

}
