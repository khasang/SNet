package io.khasang.snet.repository.messaging.impl;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.repository.DataUtils;
import io.khasang.snet.repository.messaging.RegistryUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class RegistryUtilsImpl extends DataUtils<ChatRegistryUnit> implements RegistryUtils {

    @Autowired
    public RegistryUtilsImpl(SessionFactory sessionFactory) {
        super(ChatRegistryUnit.class, sessionFactory);
    }

    @Override
    public void saveRegistry(ChatRegistryUnit registry) {
        ChatRegistryUnit duplicate = this.getRegistryByChatAndUser(
                registry.getChat(),registry.getUser());
        if (duplicate==null) {
            this.add(registry);
        }
    }

    @Override
    public ChatRegistryUnit getRegistryByChatAndUser(Chat chat, User user) {
        return this.get(
                this.createEqualsExpression("chat",chat),
                this.createEqualsExpression("user", user)
        );
    }

    @Override
    public List<ChatRegistryUnit> getRegistriesByUser(User user) {
        return this.getList(this.createEqualsExpression("user", user));
    }

    @Override
    public void deleteRegistry(Chat chat, User user) {
        ChatRegistryUnit willRemoved = this.getRegistryByChatAndUser(chat, user);
        this.delete(willRemoved);
    }
}
