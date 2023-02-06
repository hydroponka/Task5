package by.ageenko.task5.loader;

import by.ageenko.task5.exception.CustomRunTimeException;
import by.ageenko.task5.entity.Pier;
import by.ageenko.task5.entity.Port;
import by.ageenko.task5.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
    static Logger logger = LogManager.getLogger();
    private Port port = Port.getInstanse();
    private TimeUnit time = TimeUnit.SECONDS;
    private Pier pier;

    public Loader(Pier pier) {
        this.pier = pier;
    }

    @Override
    public void run() {
        while (true) {
            try {
                time.sleep(5);
                Thread.currentThread().setName("Pier " + pier.getId());
                Ship ship = port.getShip();
                if (ship != null) {
                    while (ship.countCheck()) {
                        time.sleep(1);
                        ship.addContainer(1);
                        port.removeContainer(1);
                        logger.log(Level.INFO, ship.getContainerAmount() + " Loaded container on ship " +ship.getShipId() + " "+ Thread.currentThread().getName());
                        logger.log(Level.INFO, port.getStockSize() + "  container in stock.");
                    }
                    logger.log(Level.INFO,"The ship id = {} has finished loading at the pier {}", ship.getShipId(), pier.getId());
                }
            } catch (InterruptedException e) {
                throw new CustomRunTimeException(e);
            }
        }
    }
}
