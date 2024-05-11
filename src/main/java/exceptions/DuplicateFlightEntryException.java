package exceptions;

public class DuplicateFlightEntryException extends RuntimeException{
    public DuplicateFlightEntryException(String message) {
        super(message);
    }
}
