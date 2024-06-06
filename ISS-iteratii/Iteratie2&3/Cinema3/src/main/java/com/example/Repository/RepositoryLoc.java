package com.example.Repository;

import com.example.Model.Admin;
import com.example.Model.Loc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RepositoryLoc implements IRepository<Integer, Loc>{

    private SessionFactory sessionFactory;

    public RepositoryLoc(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Loc entity) {

    }

    @Override
    public void delete(Loc entity) {

    }

    @Override
    public void update(Loc entity) {
        try (var session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx != null)
                    tx.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loc findById(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Loc> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT A FROM Loc A", Loc.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }

    public Iterable<Loc> findAllReserved(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT A FROM Loc A WHERE A.stare = 1", Loc.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
