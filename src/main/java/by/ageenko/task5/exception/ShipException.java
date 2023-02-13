package by.ageenko.task5.exception;

public class ShipException extends Exception{
    public ShipException() {
    }

    public ShipException(String message) {
        super(message);
    }

    public ShipException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipException(Throwable cause) {
        super(cause);
    }
}
