package by.ageenko.task5.entity;

import by.ageenko.task5.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Ship extends Thread {
    static Logger logger = LogManager.getLogger();
    public static final int MAX_CAPACITY = 100;
    public static final int MIN_CAPACITY = 0;
    private TimeUnit time = TimeUnit.SECONDS;
    private int shipId;
    private int containerAmount;
    public enum StateShip{
        LOAD, UNLOAD, LOAD_UNLOAD
    }
    private StateShip stateShip;

    public Ship(int containerAmount , StateShip stateShip) {
        this.shipId = IdGenerator.generateIdOfShip();
        this.containerAmount = containerAmount;
        this.stateShip = stateShip;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(int containerAmount) {
        this.containerAmount = containerAmount;
    }

    public StateShip getStateShip() {
        return stateShip;
    }

    public void setStateShip(StateShip stateShip) {
        this.stateShip = stateShip;
    }

    @Override
    public synchronized void run() {
        Port port = Port.getInstanse();
        Pier pier = null;
        try {
            pier = port.getPier();
            if (pier != null) {
                switch (stateShip) {
                    case LOAD -> {
                        port.load(100);
                        logger.log(Level.INFO, "100 conteiners load to ship " + shipId + " from " + pier.getId());
                    }
                    case UNLOAD -> port.unload(20);
                    case LOAD_UNLOAD -> {
                        port.unload(3);
                        port.load(4);
                    }
                }
            }
        } finally {
            port.releasePier(pier);
        }
    }
}
