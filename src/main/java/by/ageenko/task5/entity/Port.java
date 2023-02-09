package by.ageenko.task5.entity;

import by.ageenko.task5.exception.CustomRuntimeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static Port instanse;
    static Logger logger = LogManager.getLogger();
    static final int MAX_STOCK_SIZE = 1000;
    static final int MIN_STOCK_SIZE = 0;
    static final int DEFAULT_STOCK_SIZE = 500;
    private static Lock locker = new ReentrantLock(true);
    private static AtomicBoolean create = new AtomicBoolean(false);
    private TimeUnit time = TimeUnit.SECONDS;
    private AtomicInteger portContainers = new AtomicInteger(DEFAULT_STOCK_SIZE);
    private ArrayDeque<Pier> piers;
    private Train train;

    private Port() {

    }
    public static Port getInstanse() {
        if (!create.get()){
            locker.lock();
            try {
                if (instanse == null){
                    instanse = new Port();
                    create.getAndSet(true);
                }
            }finally {
                locker.unlock();
            }
        }
        return instanse;
    }

    public AtomicInteger getPortContainers() {
        return portContainers;
    }

    public void setPortContainers(AtomicInteger portContainers) {
        this.portContainers = portContainers;
    }

    public void setPiers(ArrayDeque<Pier> piers) {
        this.piers = piers;
    }

    public Pier getPier(){
        locker.lock();
        Pier pier;
        try {
            pier = piers.poll();
        } finally {
            locker.unlock();
        }
        return pier;
    }
    public void releasePier(Pier pier){
        locker.lock();
        try {
            piers.add(pier);
        }finally {
            locker.unlock();
        }
    }

    public synchronized int load(int container) {
        if (portContainers.get() > MAX_STOCK_SIZE * 0.2) {
            portContainers.addAndGet(-container);
            System.out.println("container in port = " + portContainers.get());
        } else {
            try {
                train.start();
            } catch (Exception e) {
                throw new CustomRuntimeException(e);
            }
        }
        return container;
    }

    public int unload(int container) {
        if (portContainers.get() < MAX_STOCK_SIZE * 0.8) {
            portContainers.addAndGet(container);
        } else {
            locker.lock();
            try {
                train.start();
            } finally {
                locker.unlock();
            }
        }
        return container;
    }

}
