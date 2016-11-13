package io.khasang.snet.entity.workgroups;

import javax.persistence.*;

@Entity
@Table(name = "userWorkgroups")
public class UserWorkgroups {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @EmbeddedId
    private UserWorkgroupsId prKey;

    // Позволяет добавлять новости в группу
    private boolean isAdmin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserWorkgroupsId getPrKey() {
        return prKey;
    }

    public void setPrKey(UserWorkgroupsId prKey) {
        this.prKey = prKey;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
