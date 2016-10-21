package io.khasang.snet.util;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Picture;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class PictureDataUtilTest {

    @Autowired
    private Generator<Picture> generator;

    @Test
    public void pictureEqualsTest() {
        Picture first = generator.create();
        Picture same = first;
        Picture another = generator.create();
        assertEquals(first,same);
        assertNotEquals(first, another);
    }

}
