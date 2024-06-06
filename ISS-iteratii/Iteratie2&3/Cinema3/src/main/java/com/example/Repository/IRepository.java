package com.example.Repository;

public interface IRepository<Id, T> {
    void add(T entity);
    void delete(T entity);
    void update(T entity);
    T findById(Id id);
    Iterable<T> findAll();
}
