package io.khasang.snet.model;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.HibernateDAO;
import io.khasang.snet.entity.WeatherReport;
import io.khasang.snet.util.Generator;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;
import java.util.HashSet;

/**
 * Test weather report
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class, WebConfig.class})
public class WeatherReportTest {


    private HibernateDAO<WeatherReport> reportUtils;

    private Generator<WeatherReport> generator;

    private DataBaseTestUtilities4Entities<WeatherReport> entitiesTestUtil;

    @Before
    public void setUp() {
        if (this.entitiesTestUtil==null)
            this.entitiesTestUtil = new DataBaseTestUtilities4Entities<>(reportUtils);
    }

    @Test
    public void equalsTest() {
        WeatherReport first = generator.create();
        WeatherReport same = new WeatherReport(
                first.getID(),
                first.getCity(),
                first.getLowestTemp(),
                first.getHighestTemp(),
                first.getTimeStamp());
        WeatherReport another = generator.create();
        entitiesTestUtil.testEquals(first, same, another);
    }

    @Test
    public void saveAndLoadTest() {
        WeatherReport original = generator.create();
        WeatherReport deSerialized = entitiesTestUtil.testSaveAndLoad(original);
        assertEquals("Saving and loading test failed",original,deSerialized);
    }

    @Test
    public void updateTest() {
        WeatherReport original = generator.create();
        WeatherReport different = generator.create();
        different.setCity(original.getCity());
        different.setTimeStamp(original.getTimeStamp());

        WeatherReport deSerialized = entitiesTestUtil.testUpdate(original,different);
        assertNotEquals("Original and different must differs",original, different);
        assertEquals("Edited report differ from deSerialized",different,deSerialized);
    }

    @Test
    public void deleteTest() {
        WeatherReport report = generator.create();
        assertNull(report);
    }

    @Test
    public void collectionTest() {
        Collection<WeatherReport> collection = new HashSet<>();
        assertEquals(0, entitiesTestUtil.testForLists(collection));
    }
}
