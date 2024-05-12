package dao;

import model.Flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDAOInMemory implements FlightDAO {

    List<Flight> flights = new ArrayList<>();

    @Override
    public Flight getFlightById(int id) {
        return flights.stream().filter(i->i.getId()==id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void deleteFlight(Flight flight) {
        getAllFlights().remove(flight);
    }

    @Override
    public void saveFlight(Flight flight) {
        flights.add(flight);
    }

    @Override
    public Collection<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public boolean existById(Flight flight) {
        for(Flight f: getAllFlights()){
            if(f.getId() == flight.getId()){
                return true;

            }
        }

        return false;
    }
}
