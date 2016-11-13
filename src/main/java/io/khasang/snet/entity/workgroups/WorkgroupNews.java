package io.khasang.snet.entity.workgroups;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkgroupNews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long workgroupId;

    private String title;

    @Column(length = 5000)
    private String description;

    private String newsDate;

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

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }


    // ТЗ
// UserNews - все новости пользователя всех групп в которых он состоит
// Workgroups - список групп в которых состоиит пользователь
// Cтраница Группы - отображение всех новостей этой группы
// "Админка" Manage groups - создание групп и новостей , добавление пользователей в группы
//
// Тесты на роли, ( 2 новые роли) добавление этих для Neo и Morpheus
// Создание Department - 2ух Units, по 1 группе
}

