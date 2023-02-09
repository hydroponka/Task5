package by.ageenko.task5.main;

import by.ageenko.task5.entity.Pier;
import by.ageenko.task5.entity.Port;
import by.ageenko.task5.entity.Ship;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
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
        Ship ship = new Ship(0, Ship.StateShip.LOAD);
        Ship ship1 = new Ship(0, Ship.StateShip.LOAD);
        Ship ship2 = new Ship(0, Ship.StateShip.LOAD);
        Ship ship3 = new Ship(0, Ship.StateShip.LOAD);
        Ship ship4 = new Ship(0, Ship.StateShip.LOAD);
        Ship ship5 = new Ship(0, Ship.StateShip.LOAD);
        Ship ship6 = new Ship(0, Ship.StateShip.LOAD);
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.submit(ship);
        service.submit(ship1);
        service.submit(ship2);
        service.submit(ship3);
        service.submit(ship4);
        service.submit(ship5);
        service.shutdown();

    }
}
