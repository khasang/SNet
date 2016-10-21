package io.khasang.snet.util;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.HibernateDAO;
import io.khasang.snet.dao.impl.PicturesDataUtil;
import io.khasang.snet.entity.Picture;
import static org.junit.Assert.*;

import io.khasang.snet.util.common.PicturesGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class PictureDataUtilTest {

    @Autowired
    private Generator<Picture> generator;

    @Autowired
    private HibernateDAO<Picture, Long> picturesDataUtil;

    @Test
    public void pictureEqualsTest() {
        Picture first = generator.create();
        Picture same = first;
        Picture another = generator.create();
        assertEquals(first,same);
        assertNotEquals(first, another);
    }

    @Test
    public void saveAndLoadTest() {
        Picture original = generator.create();
        picturesDataUtil.add(original);

        Picture deSerialized = picturesDataUtil.get(original.getId());
        assertEquals(original,deSerialized);
    }

    @Test
    public void updateTest() {
        Picture original = generator.create();
        picturesDataUtil.add(original);

        Picture differed = generator.create();
        differed.setId(original.getId());
        differed.setDescription(original.getDescription());
        differed.setOwnerId(original.getOwnerId());
        assertNotEquals("Failed: original and differed haven't difference", original, differed);

        picturesDataUtil.edit(differed);
        Picture deSerialized = picturesDataUtil.get(differed.getId());
        assertEquals("Failed: objects differs after serialization", differed, deSerialized);
        assertNotEquals("Failed original and edited object haven't defference", original, deSerialized);
    }

    @Test
    public void deleteTest() {
        Picture picture = generator.create();
        picturesDataUtil.add(picture);

        picturesDataUtil.delete(picture.getId());
        Picture deleted = picturesDataUtil.get(picture.getId());
        assertNull(deleted);
    }

    @Test
    public void listReturnTest() {
        Set<Picture> pictureSet = new HashSet<>();
        for (Picture picture : new PicturesGenerator(10)) {
            picturesDataUtil.add(picture);
            pictureSet.add(picture);
        }

        List<Picture> pictures = picturesDataUtil.getAll();
        pictureSet.removeAll(pictures);
        assertEquals(0,pictureSet.size());
    }
}
