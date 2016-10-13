import io.khasang.snet.config.AppConfig;
import io.khasang.snet.service.QueryHandler;
import io.khasang.snet.service.UsersPasswordChanger;
import org.junit.Assert;
import org.junit.Ignore;
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
    @Test@Ignore
    public void queryUpdateTest() {
        Integer rowAffected = queryHandler.executeUpdate(preparedStatementCreate("god","user"));
        Assert.assertNotEquals("Error while quering.",new Integer(-1),rowAffected);
    }

    /* Creation PreparedStatementCreator implementation
    *  @param updating value
    *  @param key value
    *  */
    private PreparedStatementCreator preparedStatementCreate(String value, String key) {
        /* WARNING: Configure your query */
        String sql = "UPDATE users SET password = ? WHERE login = ?";
        return connection -> {
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            statement.setString(1,value);
            statement.setString(2,key);
            return statement;
        };
    }

    @Test
    public void changerTest() {
        String response = new UsersPasswordChanger(queryHandler).change("user","wooow");
        if (response.contains("Fail")) Assert.fail("Failed");
        System.out.println(response);
    }
}
