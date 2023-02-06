package by.ageenko.task5.entity;

import by.ageenko.task5.Exception.CustomRunTimeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Port {
    private static final Port instanse = new Port();
    static Logger logger = LogManager.getLogger();
    static final int MAX_STOCK_SIZE = 100;
    static final int MIN_STOCK_SIZE = 0;
    static final int DEFAULT_STOCK_SIZE = 50;

    private TimeUnit time = TimeUnit.SECONDS;
    private int stockSize;
    private List<Ship> store = new ArrayList<>();

    private Port() {
        stockSize = DEFAULT_STOCK_SIZE;
    }


    public static Port getInstanse() {
        return instanse;
    }

    public int getStockSize() {
        return stockSize;
    }

    public void addContainer(int stockSize) {
        this.stockSize += stockSize;
    }

    public void removeContainer(int stockSize) {
        this.stockSize -= stockSize;
    }

    public List<Ship> getStore() {
        return store;
    }


    public void addShip(Ship ship) {
        store.add(ship);
    }

    public synchronized Ship getShip() {
        if (!store.isEmpty()) {
            for (Ship ship : store) {
                store.remove(ship);
                return ship;
            }
        } else {
            logger.log(Level.WARN, "There are no ships in store = {}", store);
        }
        return null;
    }

    public synchronized boolean isStockFull() {
        return stockSize != MAX_STOCK_SIZE;
    }

    public synchronized boolean isStockEmpty() {
        return stockSize != MIN_STOCK_SIZE;
    }
}
