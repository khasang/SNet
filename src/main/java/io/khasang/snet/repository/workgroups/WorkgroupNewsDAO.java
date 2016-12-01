package io.khasang.snet.repository.workgroups;

import io.khasang.snet.entity.workgroups.WorkgroupNews;

import java.util.List;

public interface WorkgroupNewsDAO  {

    void addWorkgroupNews(WorkgroupNews workgroupNews);

    WorkgroupNews getWorkgroupNews(Long id);

    void updateWorkgroup(WorkgroupNews workgroup);

    void deleteWorkgroup(WorkgroupNews workgroup);

    List<WorkgroupNews> getAllWorkgroupNewsList(Long workgroupId);

    List<WorkgroupNews> getAllUserWorkgroupNewsList(List<Long> workgroupIdList);

}
