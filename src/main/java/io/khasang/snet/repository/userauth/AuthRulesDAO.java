package io.khasang.snet.repository.userauth;

import io.khasang.snet.entity.userauth.AuthRules;
import java.util.List;

public interface AuthRulesDAO {
    void addAuthRules(AuthRules authRules);
    void deleteAllUserAuthRules(int user_id);
    void deleteAllRoleAuthRules(int role_id);
    List<AuthRules> getAllUserAuthRules(int user_id);
}
