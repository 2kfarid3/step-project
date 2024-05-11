package service;

import dao.FlightDAO;
import dto.FlightDTO;
import exceptions.DuplicateFlightEntryException;
import exceptions.FlightNotFoundException;
import model.Flight;

import java.time.LocalDateTime;
import java.util.Scanner;

public class FlightsService {
    FlightDAO flightsDAO;

    public FlightsService(FlightDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    public FlightDTO createFlight(FlightDTO flightDTO){
        Flight flight = new Flight(flightDTO.getOrigin(), flightDTO.getDestination(), flightDTO.getDepartureTime(), flightDTO.getAvailableSeats());

        if(flightsDAO.existById(flight)){
            throw new DuplicateFlightEntryException("Flight already exists!");
        }
        flightsDAO.saveFlight(flight);

        return new FlightDTO(flight.getOrigin(), flight.getDestination(), flight.getDepartureTime(), flight.getAvailableSeats());
    }

    public void displayOnlineBoard() {
        System.out.println("Displaying flights within the next 24 hours :");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24hours = now.plusHours(24);
        boolean flightFound = false;

        for (Flight flight : flightsDAO.getAllFlights()) {
            if (flight.getOrigin().equals("Kiev") && flight.getDepartureTime().isAfter(now) && flight.getDepartureTime().isBefore(next24hours)) {
                printFlight(flight);
                flightFound = true;
            }
        }

        if (!flightFound) {
            throw new FlightNotFoundException("FLight within 24 hours were not found!");
        }

    }

    public FlightDTO getFlightInfo() {
        System.out.println("Enter flight ID:");
        int id = new Scanner(System.in).nextInt();
        boolean flightFound = false;
        Flight foundFlight = new Flight();
        for (Flight flight : flightsDAO.getAllFlights()) {
            if (flight.getId() == id) {
                foundFlight = flight;
                flightFound = true;
                break;
            }
        }

        if (!flightFound) {
            throw new FlightNotFoundException("Flight was not found");
        }

        return new FlightDTO(foundFlight.getId(),foundFlight.getOrigin(),foundFlight.getDestination(),foundFlight.getDepartureTime(),foundFlight.getAvailableSeats());
    }

    public FlightDTO searchFlight(){
        System.out.println("Enter the destination:");

        String destination = new Scanner(System.in).next();

        System.out.println("Enter date as (YYYY-MM-DDTHH:MM):");

        LocalDateTime date = LocalDateTime.parse(new Scanner(System.in).next());


        boolean flightFound = false;

        Flight foundFlight = new Flight();

        for (Flight f : flightsDAO.getAllFlights()) {
            if (f.getDestination().equalsIgnoreCase(destination) && f.getDepartureTime().isEqual(date)) {
                flightFound = true;
                foundFlight = f;

                break;
            }
        }

        if(!flightFound){
            throw new FlightNotFoundException("Flight not found");
        }


        return new FlightDTO(foundFlight.getId(),foundFlight.getOrigin(), foundFlight.getDestination(), foundFlight.getDepartureTime(),foundFlight.getAvailableSeats());
    }

    public void printFlight(Flight f){
        System.out.println("Flight ID: " + f.getId());
        System.out.println("Origin: " + f.getOrigin());
        System.out.println("Destination: " + f.getDestination());
        System.out.println("Departure Time: " + f.getDepartureTime());
        System.out.println("Available Seats: " + f.getAvailableSeats());
    }

}
