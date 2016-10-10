import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@PropertySource("src/main/resource/dbconfig.properties")
public class TestConnection {
    private Connection connection;
    private StringBuilder url;

    @Before
    public void setUpAll() {
        url = new StringBuilder();
        // database type
        url.append("jdbc:postgresql://");
        // host name
        url.append("localhost:");
        // port
        url.append("5432/");
        // database name
        url.append("test_database?");
        // username
        url.append("user=root&");
        // password
        url.append("password=root");

        System.out.println("Request: " + url);

    }

    @Test
    public void registerDriverTest() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
           Assert.fail("Driver registration failed: " + e);
            Assert.assertTrue(true);
        }
    }

    @Test
    public void getConnectionTest() {
        try {
            connection = DriverManager.getConnection(url.toString());
        } catch (SQLException exc) {
            Assert.fail("Getting connection throw error " + exc);
            Assert.assertTrue(true);
        }

        Assert.assertNotNull(connection);
    }

    @Test
    public void dataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("user"));
        dataSource.setPassword(environment.getProperty("password"));

        try {
            Assert.assertTrue(dataSource.getConnection()==null);
        } catch (SQLException exc) {
            Assert.fail();
        }
    }

}
