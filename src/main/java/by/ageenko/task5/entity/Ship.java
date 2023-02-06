package by.ageenko.task5.entity;

import by.ageenko.task5.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;

public class Ship {
    static Logger logger = LogManager.getLogger();
    public static final int MAX_CAPACITY = 10;
    public static final int MIN_CAPACITY = 0;
    private int shipId;
    private int containerAmount;

    public Ship(int containerAmount) {
        this.shipId = IdGenerator.generateIdOfShip();
        if (containerAmount <= MAX_CAPACITY && containerAmount >= MIN_CAPACITY) {
            this.containerAmount = containerAmount;
        }else {
            this.containerAmount = MIN_CAPACITY;
        }
    }

    public int getShipId() {
        return shipId;
    }

    public int getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(int containerAmount) {
        this.containerAmount = containerAmount;
    }
    public void addContainer(int containerAmount){
            this.containerAmount += containerAmount;
    }
    public void removeContainer(int containerAmount){
            this.containerAmount -= containerAmount;
    }
    public boolean countCheck(){
        if (this.containerAmount >= MAX_CAPACITY || this.containerAmount <= MIN_CAPACITY){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("shipId=" + shipId)
                .add("containerAmount=" + containerAmount)
                .toString();
    }
}
