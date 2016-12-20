package io.khasang.snet.repository.messaging;

import io.khasang.snet.config.AppContext;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.repository.userauth.UserDAO;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, HibernateConfig.class, WebConfig.class})
public class ChatTest {
    private static final Logger LOGGER = Logger.getLogger(RegistryTest.class);

    @Autowired
    private UserDAO userDAO;


}
