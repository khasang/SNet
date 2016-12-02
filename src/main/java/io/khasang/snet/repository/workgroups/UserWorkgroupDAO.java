package io.khasang.snet.repository.workgroups;

import io.khasang.snet.entity.workgroups.UserWorkgroups;

import java.util.List;

public interface UserWorkgroupDAO {

    void addUserWorkgroups(UserWorkgroups userWorkgroupDAO);
    void updateUserWorkgroups(UserWorkgroups userWorkgroupDAO);
    void deleteUserWorkgroups(UserWorkgroups userWorkgroupDAO);
    List<Long> getWorkgroupsByUser(long userId);
    List<Long> getUsersByWorkgroup(long workGroupId);

}

