package com.nameNotFound.javaChess.utils.paterns.observer;

import java.util.ArrayList;

public abstract class Observable {
    protected final ArrayList<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        this.observers.add(o);
    }

    public void detach(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObs() {
        for (Observer o: observers)
            o.update();
    }
}
