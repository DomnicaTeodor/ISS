package com.example.Repository;

import com.example.Model.Bilet;
import com.example.Model.Loc;
import org.hibernate.SessionFactory;

import java.util.List;

public class RepositoryBilet implements IRepository<Integer, Bilet>{

    SessionFactory sessionFactory;

    public RepositoryBilet(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Bilet entity) {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Bilet entity) {

    }

    @Override
    public void update(Bilet entity) {

    }

    @Override
    public Bilet findById(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Bilet> findAll() {
        try(var session = sessionFactory.openSession()){
            return session.createQuery("SELECT A FROM Bilet A", Bilet.class).list();
        }
        catch (Exception e){
            return List.of();
        }
    }
}
