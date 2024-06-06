package com.example.Observer;

public interface Observer<E extends Event>{
    public void update(E t);
}
