package io.khasang.snet.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class By {
    public By(String byMessage) {
        this.byMessage = byMessage;
    }

    private String byMessage;

    public By() {
    }

    public String getByMessage() {
        return "By";
    }

    public void setByMessage(String byMessage) {
        this.byMessage = byMessage;
    }
}
