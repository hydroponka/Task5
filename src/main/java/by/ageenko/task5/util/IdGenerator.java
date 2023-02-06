package by.ageenko.task5.util;

public class IdGenerator {
    private static int idOfShip;
    private static int idOfPier;

    private IdGenerator() {
    }

    public static int generateIdOfShip() {
        idOfShip++;
        return idOfShip;
    }
    public static int generateIdOfPier() {
        idOfPier++;
        return idOfPier;
    }
}
