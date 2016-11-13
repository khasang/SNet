package io.khasang.snet.service.workgroups;

import io.khasang.snet.dao.workgroups.UserWorkgroupDAO;
import io.khasang.snet.dao.workgroups.WorkgroupNewsDAO;
import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkgroupNewsService {

    private WorkgroupNewsDAO workgroupNewsDAO;
    private UserWorkgroupDAO userWorkgroupDAO;

    @Autowired
    public WorkgroupNewsService(WorkgroupNewsDAO workgroupNewsDAO, UserWorkgroupDAO userWorkgroupDAO) {
        this.workgroupNewsDAO = workgroupNewsDAO;
        this.userWorkgroupDAO = userWorkgroupDAO;

    }

    public void addWorkgroupNews(WorkgroupNews workgroupNews) {
        workgroupNewsDAO.addWorkgroupNews(workgroupNews);

    }

    public List<WorkgroupNews> getAllWorkgroupNewsList(Long workgroupId) {

        return workgroupNewsDAO.getAllWorkgroupNewsList(workgroupId);
    }

    public List<WorkgroupNews> getAllUserWorkgroupNewsList(long userId) {
        List<Long> userGroupsListId = userWorkgroupDAO.getWorkgroupsByUser(userId);
        return workgroupNewsDAO.getAllUserWorkgroupNewsList(userGroupsListId);
    }

    public void deleteWorkgroupNew(WorkgroupNews workgroupNews) {
        workgroupNewsDAO.deleteWorkgroup(workgroupNews);

    }

    public void getWorkgroupNew(Long id) {
        workgroupNewsDAO.getWorkgroupNews(id);
    }

    public void updateWorkgroupNew(WorkgroupNews workgroup) {
        workgroupNewsDAO.updateWorkgroup(workgroup);
    }

}
