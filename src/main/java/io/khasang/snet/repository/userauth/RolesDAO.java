package io.khasang.snet.repository.userauth;

import io.khasang.snet.entity.userauth.Roles;

public interface RolesDAO {
    void addRoles(Roles roles);
    void deleteRolesById(int id);
    void deleteRolesByName(String role);
    Roles getRolesById(int id);
    Roles getRolesByName(String name);
}
