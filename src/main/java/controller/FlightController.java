package controller;

import dto.FlightDTO;
import model.Flight;
import service.FlightsService;

public class FlightController {
    FlightsService flightsService;

    public FlightController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    public FlightDTO createFlight(FlightDTO flightDTO){
        return flightsService.createFlight(flightDTO);
    }

    public void displayOnlineBoard(){
        flightsService.displayOnlineBoard();
    }

    public FlightDTO showFlightInfo(){
        return flightsService.getFlightInfo();
    }

    public FlightDTO searchFlight(){
        return flightsService.searchFlight();
    }

    public void printFlight(Flight f){flightsService.printFlight(f);}

}
