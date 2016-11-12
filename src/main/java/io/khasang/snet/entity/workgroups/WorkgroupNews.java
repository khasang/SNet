package io.khasang.snet.entity.workgroups;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WorkgroupNews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long workgroupId;

    private String title;

    private String description;

    private Date newsDate;
}

//
// UserNews - все новости пользователя всех групп в которых он состоит
// Workgroups - список групп в которых состоиит пользователь
// Cтраница Группы - отображение всех новостей этой группы
// "Админка" Manage groups - создание групп и новостей , добавление пользователей в группы
//
// Тесты на роли, ( 2 новые роли) добавление этих для Neo и Morpheus
// Создание Department - 2ух Units, по 1 группе