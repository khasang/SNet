package io.khasang.snet.dao.workgroups;

import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupNews;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WorkgroupNewsDAOImpl implements WorkgroupNewsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public WorkgroupNewsDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addWorkgroupNews(WorkgroupNews workgroupNews) {
        sessionFactory.getCurrentSession().save(workgroupNews);

    }

    @Override
    public WorkgroupNews getWorkgroupNews(Long workgroupNewsId) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Workgroup.class);
        criteria.add(Restrictions.eq("id",workgroupNewsId));
        return (WorkgroupNews) criteria.uniqueResult();
    }

    @Override
    public void updateWorkgroup(WorkgroupNews workgroup) {
        sessionFactory.getCurrentSession().update(workgroup);
    }

    @Override
    public void deleteWorkgroup(WorkgroupNews workgroup) {
        final Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().delete(workgroup);
        session.flush();
    }

    @Override
    public List<WorkgroupNews> getAllWorkgroupNewsList(Long workgroupId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.workgroups.WorkgroupNews ww where ww.workgroupId = :workgroup");
        query.setParameter("workgroup",workgroupId);
        return (List<WorkgroupNews>) query.list();
    }

    // не тестил
    @Override
    public List<WorkgroupNews> getAllUserWorkgroupNewsList(List<Long> workgroupIdList) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.workgroups.WorkgroupNews w where w.workgroupId in :workgroups");
        query.setParameterList("workgroups",workgroupIdList);
        return (List<WorkgroupNews>) query.list();
    }


}
