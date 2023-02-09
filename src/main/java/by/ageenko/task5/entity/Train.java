package by.ageenko.task5.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Train extends Thread {
    @Override
    public void run() {
        Port port = Port.getInstanse();
        port.setPortContainers(new AtomicInteger(500));
        System.out.println("Train " + port.getPortContainers().get());
    }
}
