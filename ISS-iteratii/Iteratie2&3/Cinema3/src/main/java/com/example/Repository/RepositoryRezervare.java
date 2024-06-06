package com.example.Repository;

import com.example.Model.Rezervare;
import org.hibernate.SessionFactory;

public class RepositoryRezervare implements IRepository<Integer, Rezervare>{
    private SessionFactory sessionFactory;

    public RepositoryRezervare(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Rezervare entity) {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getLastId(){
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT MAX(id) FROM Rezervare", Integer.class).getSingleResult();
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public void delete(Rezervare entity) {

    }

    @Override
    public void update(Rezervare entity) {

    }

    @Override
    public Rezervare findById(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Rezervare> findAll() {
        return null;
    }
}
