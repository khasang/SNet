package io.khasang.snet.model;

import org.springframework.stereotype.Component;

@Component
public class By {
    private String byMessage;

    public By(String byMessage) {
        this.byMessage = byMessage;
    }

    public By() {
    }

    public String getByMessage() {
        return "By";
    }

    public void setByMessage(String byMessage) {
        this.byMessage = byMessage;

    }
}
