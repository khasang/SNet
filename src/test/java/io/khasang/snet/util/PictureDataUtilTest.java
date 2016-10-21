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

    /* There test equals method of Picture */
    @Test
    public void pictureEqualsTest() {
        Picture first = generator.create();
        Picture same = first;
        Picture another = generator.create();
        assertEquals(first,same);
        assertNotEquals(first, another);
    }

    /* Tested saving and loading of entities,
     * from database */
    @Test
    public void saveAndLoadTest() {
        Picture original = generator.create();
        picturesDataUtil.add(original);

        /* If loaded picture will differs from
        * original test fails */
        Picture deSerialized = picturesDataUtil.get(original.getId());
        assertEquals(original,deSerialized);
    }

    /* Tested updating of exists entity instance */
    @Test
    public void updateTest() {
        Picture original = generator.create();
        picturesDataUtil.add(original);

        /* Will create new different picture, but
        * fields id, description and ownerId sets from
        * original. Will differ just imageBody and
        * if it's field was same test fails */
        Picture differed = generator.create();
        differed.setId(original.getId());
        differed.setDescription(original.getDescription());
        differed.setOwnerId(original.getOwnerId());
        assertNotEquals("Failed: original and differed haven't difference", original, differed);

        /* Will edited picture in database, and after
        * it's loaded and deserialized if it will be
        * not same as different picture, already in program memory
        * test will failed unlike first checking, in next if
        * deserialized objectwill be same as original
        * (not edited) test fails */
        picturesDataUtil.edit(differed);
        Picture deSerialized = picturesDataUtil.get(differed.getId());
        assertEquals("Failed: objects differs after serialization", differed, deSerialized);
        assertNotEquals("Failed original and edited object haven't defference", original, deSerialized);
    }

    /* Tested deletion from data base */
    @Test
    public void deleteTest() {
        Picture picture = generator.create();
        picturesDataUtil.add(picture);

        /* if deleted picture not will be null test
        * will be failed*/
        picturesDataUtil.delete(picture.getId());
        Picture deleted = picturesDataUtil.get(picture.getId());
        assertNull(deleted);
    }

    /* Tested list returned by dao
    * saved set of pictures, and pictures
    * also saved into data base, after loading list
    * all same instances was deleted
    * */
    @Test
    public void listReturnTest() {
        Set<Picture> pictureSet = new HashSet<>();
        for (Picture picture : new PicturesGenerator(10)) {
            picturesDataUtil.add(picture);
            pictureSet.add(picture);
        }

        // if some picture retains in set test fails
        List<Picture> pictures = picturesDataUtil.getAll();
        pictureSet.removeAll(pictures);
        assertEquals(0,pictureSet.size());
    }
}
