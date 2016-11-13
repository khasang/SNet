package io.khasang.snet.entity.workgroups;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WorkgroupNews {


    // ТЗ
// UserNews - все новости пользователя всех групп в которых он состоит
// Workgroups - список групп в которых состоиит пользователь
// Cтраница Группы - отображение всех новостей этой группы
// "Админка" Manage groups - создание групп и новостей , добавление пользователей в группы
//
// Тесты на роли, ( 2 новые роли) добавление этих для Neo и Morpheus
// Создание Department - 2ух Units, по 1 группе

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long workgroupId;

    private String title;

    private String description;

    private Date newsDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkgroupId() {
        return workgroupId;
    }

    public void setWorkgroupId(long workgroupId) {
        this.workgroupId = workgroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }
}

