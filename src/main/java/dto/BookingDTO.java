package dto;

import dao.User;
import model.BookingType;

import java.util.ArrayList;
import java.util.List;

public class BookingDTO {
    private static int ID = 0;
    private final int bookingID;

    private int flightID;

    private String origin;

    private String destination;

    private List<User> passengers = new ArrayList<>();

    private BookingType bookingType;

    public BookingDTO(int flightID, String origin, String destination, List<User> passengers, BookingType bookingType) {
        this.bookingID = ++ID;
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.passengers = passengers;
        this.bookingType = bookingType;
    }

    public BookingDTO(int flightID, String origin, String destination) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.bookingType = BookingType.PENDING;
        bookingID = ++ID;
    }

    public BookingDTO() {
        bookingID = ++ID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @Override
    public String toString() {
        return "{" +
                "bookingID=" + bookingID +
                " , flightID=" + flightID +
                " , origin='" + origin + '\'' +
                " , destination='" + destination + '\'' +
                " , passengers=" + passengers +
                " , bookingType=" + bookingType +
                '}';
    }
}