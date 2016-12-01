package io.khasang.snet.dao;

import io.khasang.snet.config.AppContext;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Post;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, HibernateConfig.class, WebConfig.class})
public class TestPostsDAO {

    @Autowired
    private PostDAO postDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSizeAfterAddPost() {
        List<Post> posts = postDAO.getPostList();
        Post post = new Post();
        post.setTitle("Проверка добавления");
        post.setContext("Увеличение количества записей в таблице Posts после добавления поста");
        postDAO.addPost(post);
        List<Post> postsNew = postDAO.getPostList();
        Assert.assertEquals(posts.size(), postsNew.size() - 1);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testEqualsPostAfterAddPost() {
        Post post = new Post();
        post.setTitle("Проверка добавления");
        post.setContext("Проверка equals при добавлении  поста в базу данных");
        postDAO.addPost(post);
        List<Post> posts = postDAO.getPostList();
        Assert.assertEquals(post.getTitle(), posts.get(posts.size() - 1).getTitle());
        Assert.assertEquals(post.getContext(), posts.get(posts.size() - 1).getContext());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllPostsAfterAddFewPost() {
        List<Post> posts = postDAO.getPostList();
        int count = 100;
        for (int i = 0; i < count; i++) {
            postDAO.addPost(new Post());
        }
        List<Post> postsAfterAdd = postDAO.getPostList();
        Assert.assertEquals(posts.size(), postsAfterAdd.size() - count);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRemovePostAfterAddPost() {
        Post post = new Post();
        post.setTitle("Проверка добавления - удаления_____");
        post.setContext("Тест проверяет удаление поста из таблицы");
        postDAO.addPost(post);
        Assert.assertNotNull(postDAO.getPostByName(post.getTitle()));
        postDAO.deletePost(post);
        Assert.assertNull(postDAO.getPostByName(post.getTitle()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdatePostAfterAdd() {
        Post post = new Post();
        post.setTitle("Проверка добавления и изменения поста");
        post.setContext("Тест проверяет изменение поста в таблице");
        postDAO.addPost(post);
        post.setContext("Новый запись для поста");
        postDAO.updatePost(post);
        List<Post> posts = postDAO.getPostList();
        Assert.assertEquals(post.getContext(), posts.get(posts.size() - 1).getContext());
    }

}
