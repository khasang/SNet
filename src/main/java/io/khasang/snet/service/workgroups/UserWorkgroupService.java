package io.khasang.snet.service.workgroups;

import io.khasang.snet.dao.workgroups.UserWorkgroupDAO;
import io.khasang.snet.entity.workgroups.UserWorkgroups;
import io.khasang.snet.service.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserWorkgroupService {

    private UserWorkgroupDAO userWorkgroupDAO;

    @Autowired
    public UserWorkgroupService(UserWorkgroupDAO userWorkgroupDAO) {
        this.userWorkgroupDAO = userWorkgroupDAO;
    }

    public List<Long> getWorkgroupMembersList(long workgroupId){
        return userWorkgroupDAO.getUsersByWorkgroup(workgroupId);
    }
}


