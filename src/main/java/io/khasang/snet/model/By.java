package io.khasang.snet.model;

import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

@Component
public class By {
    private static final Logger log = Logger.getLogger(By.class);
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
        log.info("Our message at By is : " + byMessage);
    }
}
