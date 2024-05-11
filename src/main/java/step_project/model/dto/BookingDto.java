package step_project.model.dto;

import step_project.dao.Booking;
import step_project.dao.Passenger;

import java.util.Objects;
import java.util.Set;

public class BookingDto {
    private Set<Passenger> passengers;
    private Flight flightId;
    private int bookingId;

    public BookingDto(Set<Passenger> passengers, Flight flightId, int bookingId) {
        this.passengers = passengers;
        this.flightId = flightId;
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
        return bookingId == booking.getBookingId();
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
                ", bookingId=" + bookingId +
                '}';
    }
}
