import io.khasang.snet.config.AppConfig;
import io.khasang.snet.service.TableCreator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TableBuilderTest {

    @Autowired
    TableCreator tableCreator;

    @Test
    public void testTableCreation() {
        try {
            tableCreator.dropAndCreate();
        } catch (Exception exc) {
            Assert.fail();
        }
    }
}
