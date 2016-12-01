package io.khasang.snet.repository.impl;

import io.khasang.snet.repository.PostDAO;
import io.khasang.snet.entity.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDAOImpl implements PostDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public PostDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPost(Post post) {
        sessionFactory.getCurrentSession().save(post);

    }

    @Override
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);

    }

    @Override
    public void deletePost(Post post) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(post);
        session.flush();

    }

    @Override
    public Post getPostById(Long id){
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Post.class);
        criteria.add(Restrictions.eq("id", id));
        return (Post) criteria.uniqueResult();

    }

    @Override
    public Post getPostByName(String title) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Post.class);
        criteria.add(Restrictions.eq("title", title));
        return (Post) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getPostList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Post.class);
        return (List<Post>) criteria.list();
    }
}

