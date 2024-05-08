package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private static int  ID = 100000;
    private final int id;

    private final String origin;
    private final String destination;
    private final LocalDateTime departureTime;
    private int availableSeats;


    public Flight(String origin, String destination, LocalDateTime dateTime, int availableSeats) {
        this.id = ++ID;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = dateTime;
        this.availableSeats = availableSeats;

    }

    public int getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && availableSeats == flight.availableSeats && Objects.equals(origin, flight.origin) && Objects.equals(destination, flight.destination) && Objects.equals(departureTime, flight.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination, departureTime, availableSeats);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", dateTime=" + departureTime +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
