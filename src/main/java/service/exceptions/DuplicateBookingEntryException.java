package service.exceptions;



public class DuplicateBookingEntryException extends RuntimeException{
    public DuplicateBookingEntryException(String message) {
        super(message);
    }
}

