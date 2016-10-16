import io.khasang.snet.config.AppConfig;
import io.khasang.snet.controller.QueryHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.PreparedStatement;

/* Test for update query, specific for test table weather columns
* city, temp_lo, temp_hi, prcp, date
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UpdateQueryTest {

    @Autowired
    QueryHandler queryHandler;

    /* Failed if rowAffected equals -1
     * this value tell that some error
     * occur
     */
    @Test
    public void queryUpdateTest() {
        Integer rowAffected = queryHandler.executeUpdate(preparedStatementCreate(7,11));
        Assert.assertNotEquals("Error while quering.",new Integer(-1),rowAffected);
    }

    /* Creation PreparedStatementCreator implementation
    *  @param updating value
    *  @param key value
    *  */
    private PreparedStatementCreator preparedStatementCreate(Integer value, Integer key) {
        return connection -> {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE weather SET temp_lo = ? WHERE temp_hi = ?");
            statement.setInt(1,value);
            statement.setInt(2,key);
            return statement;
        };
    }

}
