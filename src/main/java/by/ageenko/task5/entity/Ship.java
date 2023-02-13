package by.ageenko.task5.entity;

import by.ageenko.task5.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.TimeUnit;

public class Ship extends Thread {
    static Logger logger = LogManager.getLogger();
    public static final int MAX_CAPACITY = 100;
    public static final int MIN_CAPACITY = 0;
    private int shipId;
    private int containerAmount;
    public enum StateShip{
        LOAD, UNLOAD, LOAD_UNLOAD
    }
    private StateShip stateShip;

    public Ship(int containerAmount , StateShip stateShip) {
        this.shipId = IdGenerator.generateIdOfShip();
        if (containerAmount <= MAX_CAPACITY && containerAmount >= MIN_CAPACITY) {
            this.containerAmount = containerAmount;
        } else {
            this.containerAmount = MIN_CAPACITY;
        }
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
    public void run() {
        Port port = Port.getInstanse();
        Pier pier = null;
        try {
            pier = port.getPier();
            switch (stateShip) {
                case LOAD -> {
                    int loadValue = MAX_CAPACITY - containerAmount;
                    port.load(loadValue);
                    logger.log(Level.INFO, "{} conteiners load to ship {} from Pier {}", loadValue, shipId, pier.getId());
                    logger.log(Level.INFO, "container in port = {}", port.getPortContainers());
                }
                case UNLOAD -> {
                    port.unload(containerAmount);
                    logger.log(Level.INFO, "{} conteiners unload from ship {} to Pier {}", containerAmount, shipId, pier.getId());
                    logger.log(Level.INFO, "container in port = {}", port.getPortContainers());
                }
                case LOAD_UNLOAD -> {
                    int loadValue = MAX_CAPACITY - containerAmount;
                    port.unload(containerAmount);
                    port.load(loadValue);
                    logger.log(Level.INFO, "{}/{} conteiners unload/load to/from ship {} from/to Pier {}", containerAmount, loadValue, shipId, pier.getId());
                    logger.log(Level.INFO, "container in port = {}", port.getPortContainers());
                }
            }
        } finally {
            port.releasePier(pier);
        }
    }
}
