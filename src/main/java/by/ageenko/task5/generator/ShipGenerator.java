package by.ageenko.task5.generator;

import by.ageenko.task5.Exception.CustomRunTimeException;
import by.ageenko.task5.entity.Port;
import by.ageenko.task5.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ShipGenerator implements Runnable {
    private Port port = Port.getInstanse();
    static Logger logger = LogManager.getLogger();
    private TimeUnit time = TimeUnit.SECONDS;
    private int shipCount;

    public ShipGenerator(int shipCount) {
        this.shipCount = shipCount;
    }

    @Override
    public void run() {
        int count = 0;
        logger.log(Level.INFO, "Generator ship started working");
        while (count < shipCount) {
            Thread.currentThread().setName(" Generator ship");
            count++;
            port.addShip(new Ship(getRandomContainerAmount()));
        }
        try {
            time.sleep(1);
        } catch (InterruptedException e) {
            throw new CustomRunTimeException(e);
        }
        logger.log(Level.INFO, "Generator ship stoped working");
    }
    public int getRandomContainerAmount(){
        Random random = new Random();
        return random.nextInt(Ship.MAX_CAPACITY);
    }
}
