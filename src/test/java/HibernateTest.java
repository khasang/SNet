import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.entity.CityLocation;
import io.khasang.snet.service.DataUtility;
import io.khasang.snet.service.common.CityLocationGenerator;
import io.khasang.snet.util.Generator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
public class HibernateTest {

    @Autowired
    private DataUtility<CityLocation,Long> cityLocationUtility;

    private Generator<CityLocation> generator;

    @Before
    public void setUp() {
        generator = new CityLocationGenerator();
    }

    @Test
    public void assertIFNull() {
        if (cityLocationUtility==null) Assert.fail();
    }

    @Test
    public void entityEqulation() {
        CityLocation first = generator.create();
        CityLocation same = first;
        CityLocation another = generator.create();
        Assert.assertEquals(first,same);
        Assert.assertNotEquals(first,another);
        System.out.println(first);
    }

    @Test
    public void pushAndCompareAfter() {
        CityLocation location = generator.create();
        try {
            cityLocationUtility.add(location);
        } catch (Exception exc) {
            Assert.fail(String.format("Failed writing DB operation: %s",exc));
        }

        CityLocation deSerialized = cityLocationUtility.get(location.getId());
        Assert.assertEquals(location,deSerialized);
        System.out.println(deSerialized);
    }
}
