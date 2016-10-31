package io.khasang.snet.entity.userReg;



import io.khasang.snet.model.Developer;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pr_id;

/*    @OneToOne
    @JoinColumn(name = "fk_user_name", referencedColumnName = "username")
    private User userProfile;*/

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "age")
    private short age;

    @Enumerated(EnumType.ORDINAL)
    private Developer developerLang;

    public Long getPr_id() {
        return pr_id;
    }

    public void setPr_id(Long pr_id) {
        this.pr_id = pr_id;
    }

 /*   public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }*/

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


}