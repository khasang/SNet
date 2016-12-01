package io.khasang.snet.service.workgroups;

import io.khasang.snet.repository.workgroups.UserWorkgroupDAO;
import io.khasang.snet.repository.workgroups.WorkgroupNewsDAO;
import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupNews;
import io.khasang.snet.service.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkgroupNewsService {

    private WorkgroupNewsDAO workgroupNewsDAO;
    private UserWorkgroupDAO userWorkgroupDAO;
    private WorkgroupService workgroupService;

    @Autowired
    private JsonSerializer<WorkgroupNews> workgroupNewsJsonSerializer;

    @Autowired
    public WorkgroupNewsService(WorkgroupNewsDAO workgroupNewsDAO, UserWorkgroupDAO userWorkgroupDAO, WorkgroupService workgroupService) {
        this.workgroupNewsDAO = workgroupNewsDAO;
        this.userWorkgroupDAO = userWorkgroupDAO;
        this.workgroupService = workgroupService;

    }

    public void addWorkgroupNews(WorkgroupNews workgroupNews) {
        workgroupNewsDAO.addWorkgroupNews(workgroupNews);

    }

    public String getAllWorkgroupNewsList(Long workgroupId) {

        return workgroupNewsJsonSerializer.parseToJson(workgroupNewsDAO.getAllWorkgroupNewsList(workgroupId));
    }

    public List<WorkgroupNews> getAllUserWorkgroupNewsList(long userId) {
        List<Long> userGroupsListId = userWorkgroupDAO.getWorkgroupsByUser(userId);
        if (userGroupsListId.size() !=0) {
            return workgroupNewsDAO.getAllUserWorkgroupNewsList(userGroupsListId);
        }
        else{
            return new ArrayList<WorkgroupNews>();
        }
    }

    public List<Workgroup> getAllUserWorkgroupList(long userId) {
        List<Long> userGroupsListId = userWorkgroupDAO.getWorkgroupsByUser(userId);
        if (userGroupsListId.size() !=0) {
            return workgroupService.getWorkgroupListByIdList(userGroupsListId);
        }
        else{
            return new ArrayList<Workgroup>();
        }
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
