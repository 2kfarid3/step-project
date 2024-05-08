package dao;

import model.Flight;

import java.util.List;

public interface FlightDAO {
    Flight getFlightByID(int id);
    List<Flight> getAllFlights();

    void removeFlight(Flight flight);

    void addFlight(Flight flight);
}
