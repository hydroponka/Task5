package by.ageenko.task5.main;

import by.ageenko.task5.entity.Pier;
import by.ageenko.task5.generator.ShipGenerator;
import by.ageenko.task5.loader.Loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());
        ShipGenerator shipGenerator = new ShipGenerator(5);
        Pier pier = new Pier();
        Loader loader = new Loader(pier);
        Loader loader1 = new Loader(new Pier());
        Loader loader2 = new Loader(new Pier());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(shipGenerator);
        executorService.execute(loader);
        //executorService.execute(loader1);
        //executorService.execute(loader2);
        executorService.shutdown();

    }
}
