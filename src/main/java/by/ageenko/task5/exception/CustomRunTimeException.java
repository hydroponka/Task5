package by.ageenko.task5.exception;

public class CustomRunTimeException extends RuntimeException{
    public CustomRunTimeException() {
    }

    public CustomRunTimeException(String message) {
        super(message);
    }

    public CustomRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRunTimeException(Throwable cause) {
        super(cause);
    }
}
