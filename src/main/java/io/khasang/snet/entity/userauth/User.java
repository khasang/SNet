package io.khasang.snet.entity.userauth;

import io.khasang.snet.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* For integrity with Messages sub-system
* was implemented AbstractEntity */
@Entity(name = "users")
public class User implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String login;
    private String password;
    private String mail;
    // отправка на почту регистрационных данных и подтвеждение почты
    private String telephone;
    private boolean activeUser;

    public User() {
        activeUser = true;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (activeUser != user.activeUser) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        return telephone != null ? telephone.equals(user.telephone) : user.telephone == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (activeUser ? 1 : 0);
        return result;
    }
}