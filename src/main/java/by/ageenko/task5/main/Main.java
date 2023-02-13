package by.ageenko.task5.main;

import by.ageenko.task5.entity.Pier;
import by.ageenko.task5.entity.Port;
import by.ageenko.task5.entity.Ship;
import by.ageenko.task5.exception.ShipException;
import by.ageenko.task5.reader.impl.ShipReaderImpl;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ShipException {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());
        Port port = Port.getInstanse();
        Pier pier1 = new Pier();
        Pier pier2 = new Pier();
        Pier pier3 = new Pier();
        ArrayDeque<Pier> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(pier1);
        arrayDeque.add(pier2);
        arrayDeque.add(pier3);
        port.setPiers(arrayDeque);
        ShipReaderImpl shipReader = new ShipReaderImpl();
        //Ship ship = shipReader.shipReader("data//Ship.txt");
        List<Ship> ships = shipReader.shipReaderToList("data//Ship.txt");

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Ship ship : ships){
            service.submit(ship);
        }

        service.shutdown();

    }
}
