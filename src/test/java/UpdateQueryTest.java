import io.khasang.snet.controller.SQLQueryHandler;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

/* Test for update query, specific for table
*  describing by model.WeatherReport
*/
public class UpdateQueryTest {

    /* Failed if rowAffected equals -1
     * this value tell that some error
     * occur
     */
    @Test
    public void queryUpdateTest() {
        Integer rowAffected =
                SQLQueryHandler.getInstance().executeUpdate(preparedStatementCreate(7,11));
        Assert.assertNotEquals("Error while quering.",new Integer(-1),rowAffected);
    }

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
