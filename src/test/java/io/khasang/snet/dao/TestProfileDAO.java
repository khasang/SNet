package io.khasang.snet.dao;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;

import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class, WebConfig.class})
public class TestProfileDAO {
/*    @Autowired
    ProfileDAO profileDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testFillAndGetProfile() {
        Profile profile = new Profile();
        profile.setUserId(1L);
        profile.setAge((short) 20);
        profile.setAboutMe("I'm russian developer");
        profile.setDeveloperLang(Developer.Java);

        profileDAO.fillProfile(profile);
        Profile myProfile =  profileDAO.getProfileByUserId(1L);
        Assert.assertEquals(profile, myProfile);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateProfile()  {
        Profile profile = new Profile();
        profile.setUserId(1000L);
        profile.setAge((short) 25);
        profile.setAboutMe("I'm russian developer");
        profile.setDeveloperLang(Developer.Cpp);
        profileDAO.fillProfile(profile);

        profile.setDeveloperLang(Developer.Java);
        profileDAO.updateProfile(profile);
        Profile newProfile =  profileDAO.getProfileByUserId(1000L);
        Assert.assertEquals(newProfile.getDeveloperLang(), profile.getDeveloperLang());


    }*/


}