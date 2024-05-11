package dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FlightDTO {
    private static int  ID = 100000;
    private int id;
    private  String origin;
    private  String destination;
    private LocalDateTime departureTime;
    private int availableSeats;


    public FlightDTO(String origin, String destination, LocalDateTime dateTime, int availableSeats) {
        this.id = ++ID;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = dateTime;
        this.availableSeats = availableSeats;

    }

    public FlightDTO(String origin, String destination, LocalDateTime departureTime) {
        this.id = ++ID;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public FlightDTO() {
        this.id = ++ID;
    }

    public FlightDTO(int id, String origin, String destination, LocalDateTime departureTime, int availableSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
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
        FlightDTO flightDTO = (FlightDTO) o;
        return id == flightDTO.id && availableSeats == flightDTO.availableSeats && Objects.equals(origin, flightDTO.origin) && Objects.equals(destination, flightDTO.destination) && Objects.equals(departureTime, flightDTO.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination, departureTime, availableSeats);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                " , origin='" + origin + '\'' +
                " , destination='" + destination + '\'' +
                " , dateTime=" + departureTime +
                " , availableSeats=" + availableSeats +
                '}';
    }

}
