package com.example.Observer;

public interface Observable<E extends Event>{
    void addObserver(Observer<E> o);

    void removeObserver(Observer<E> o);

    void notify(E t);
}
