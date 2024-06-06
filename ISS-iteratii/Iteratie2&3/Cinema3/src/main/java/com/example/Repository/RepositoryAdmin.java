package com.example.Repository;

import com.example.Model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RepositoryAdmin implements IRepository<Integer, Admin>{

    private SessionFactory sessionFactory;

    public RepositoryAdmin(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Admin entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(entity);
                tx.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Admin entity) {

    }

    @Override
    public void update(Admin entity) {

    }

    @Override
    public Admin findById(Integer id) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class, id);
        session.close();
        return admin;
    }

    @Override
    public Iterable<Admin> findAll() {
        Iterable<Admin> users = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                users = session.createQuery("select Admin as u", Admin.class)
                        .list();
                transaction.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return users;
    }

    public Admin findByUsernameAndPassword(String username, String password) {
        try(var session = sessionFactory.openSession()){
            var query = session.createQuery("SELECT C FROM Admin C WHERE C.username = :username AND C.password = :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
        catch (Exception e){
            return null;
        }
    }
}
