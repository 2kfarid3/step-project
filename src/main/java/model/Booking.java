package model;

import dao.User;

import java.util.ArrayList;
import java.util.List;

public class Booking {

    private static int ID = 0;
    private final int bookingID;

    private int flightID;

    private List<User> passengers = new ArrayList<>();

    private BookingType bookingType;

    public Booking(int flightID, List<User> passengers, BookingType bookingType) {
        this.bookingID = ++ID;
        this.flightID = flightID;
        this.passengers = passengers;
        this.bookingType = bookingType;
    }

    public Booking(int flightID) {
        this.flightID = flightID;
        this.bookingType = BookingType.PENDING;
        bookingID = ++ID;
    }

    public Booking() {
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
                " , passengers=" + passengers +
                " , bookingType=" + bookingType +
                '}';
    }
}