package io.khasang.snet.service.userReg;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
