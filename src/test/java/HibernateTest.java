import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.entity.CityLocation;
import io.khasang.snet.service.DataUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class})
public class HibernateTest {

    @Autowired
    DataUtility<CityLocation,Long> cityLocationUtility;

    @Test
    public void assertIFNull() {
        if (cityLocationUtility==null) Assert.fail();
    }

    @Test
    public void pushAndPop() {
        CityLocation location = new CityLocation();

    }
}
