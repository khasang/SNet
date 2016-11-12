package io.khasang.snet.dao.workgroups;

import io.khasang.snet.entity.workgroups.Workgroup;
import io.khasang.snet.entity.workgroups.WorkgroupType;
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
public class WorkgroupDAOImpl implements WorkgroupDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public WorkgroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addWorkgroup(Workgroup workgroup) {
        sessionFactory.getCurrentSession().save(workgroup);
    }

    @Override
    public void updateWorkgroup(Workgroup workgroup) {
        sessionFactory.getCurrentSession().update(workgroup);
    }

    @Override
    public Workgroup getWorkgroupById(long workgroupId) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Workgroup.class);
        criteria.add(Restrictions.eq("id",workgroupId));
        return (Workgroup) criteria.uniqueResult();
    }

    @Override
    public void deleteWorkgroup(Workgroup workgroup) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(workgroup);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Workgroup> getWorkgroupList(Long[] workgroupIdList) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.workgroups.Workgroup w where w.id in :workgroups");
        query.setParameterList("workgroups",workgroupIdList);
        return (List<Workgroup>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Workgroup> getAllWorkgroupList() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Workgroup.class);
        return (List<Workgroup>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Workgroup> getAllDepartments() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Workgroup.class);
        criteria.add(Restrictions.eq("workgroupType", WorkgroupType.DEPARTMENT));
        return (List<Workgroup>) criteria.list();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Workgroup> getDependentUnits(long workgroupId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Workgroup.class);
        criteria.add(Restrictions.eq("workgroupType", WorkgroupType.UNIT));
        criteria.add(Restrictions.eq("headWorkgroupId", workgroupId));
        return (List<Workgroup>) criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Workgroup> getDependentGroups(long workgroupId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Workgroup.class);
        criteria.add(Restrictions.eq("workgroupType", WorkgroupType.GROUP));
        criteria.add(Restrictions.eq("headWorkgroupId", workgroupId));
        return (List<Workgroup>) criteria.list();
    }
}
