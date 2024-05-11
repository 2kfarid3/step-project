package step_project.dao;

import step_project.model.enumm.BookingEnum;

import java.util.Objects;
import java.util.Set;

public class Booking {
    private Set<Passenger> passengers;
    private Flight flightId;
    private BookingEnum bookingEnum;
    private int bookingId;

    public Booking(Set<Passenger> passengers, Flight flightId, BookingEnum bookingEnum, int bookingId) {
        this.passengers = passengers;
        this.flightId = flightId;
        this.bookingEnum = bookingEnum;
        this.bookingId = bookingId;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Flight getFlightId() {
        return flightId;
    }

    public BookingEnum getBookingEnum() {
        return bookingEnum;
    }

    public void setBookingEnum(BookingEnum bookingEnum) {
        this.bookingEnum = bookingEnum;
    }

    public void setFlightId(Flight flightId) {
        this.flightId = flightId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "passengers=" + passengers +
                ", flightId=" + flightId +
                ", bookingEnum=" + bookingEnum +
                ", bookingId=" + bookingId +
                '}';
    }
}






