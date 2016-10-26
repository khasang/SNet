package io.khasang.snet.entity;

import io.khasang.snet.model.Developer;
import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
public class Profile {

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // тут сзязь с User - One to One
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "age")
    private short age;

    @Enumerated(EnumType.ORDINAL)
    private Developer developerLang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Developer getDeveloperLang() {
        return developerLang;
    }

    public void setDeveloperLang(Developer developerLang) {
        this.developerLang = developerLang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (age != profile.age) return false;
        if (!userId.equals(profile.userId)) return false;
        if (aboutMe != null ? !aboutMe.equals(profile.aboutMe) : profile.aboutMe != null) return false;
        return developerLang == profile.developerLang;

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + (aboutMe != null ? aboutMe.hashCode() : 0);
        result = 31 * result + (int) age;
        result = 31 * result + (developerLang != null ? developerLang.hashCode() : 0);
        return result;
    }
}