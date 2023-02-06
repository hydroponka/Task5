package by.ageenko.task5.entity;

import by.ageenko.task5.util.IdGenerator;

import java.util.StringJoiner;
import java.util.concurrent.Semaphore;

public class Pier {
    private int id;
    private boolean place;

    public Pier() {
        this.id = IdGenerator.generateIdOfPier();
        this.place = true;
    }
    public synchronized int getId() {
        return id;
    }

    public synchronized boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pier.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
