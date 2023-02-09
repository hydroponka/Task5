package by.ageenko.task5.entity;

import by.ageenko.task5.util.IdGenerator;

import java.util.StringJoiner;
import java.util.concurrent.Semaphore;

public class Pier {
    private int id;

    public Pier() {
        this.id = IdGenerator.generateIdOfPier();
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pier.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
