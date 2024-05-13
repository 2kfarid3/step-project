package flightservicetests;

import dao.FlightDAO;
import dto.FlightDTO;
import exceptions.DuplicateFlightEntryException;
import model.Flight;
import org.junit.Test;
import org.mockito.Mockito;
import service.FlightsService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightServiceTest {
    @Test
    public void createFlight_newFlightCreated() {
        FlightDTO flightDTO = new FlightDTO("Origin", "Destination", LocalDateTime.now(), 100);
        FlightDAO flightDAO = Mockito.mock(FlightDAO.class);
        Mockito.when(flightDAO.existById(Mockito.any(Flight.class))).thenReturn(false);

        FlightsService flightsService = new FlightsService(flightDAO);

        FlightDTO createdFlight = flightsService.createFlight(flightDTO);

        assertNotNull(createdFlight);
    }

    @Test
    public void createFlight_duplicateFlightEntry_throwsDuplicateFlightEntryException() {
        FlightDTO flightDTO = new FlightDTO("Origin", "Destination", LocalDateTime.now(), 100);
        FlightDAO flightDAO = Mockito.mock(FlightDAO.class);
        Mockito.when(flightDAO.existById(Mockito.any(Flight.class))).thenReturn(true);

        FlightsService flightsService = new FlightsService(flightDAO);

        assertThrows(DuplicateFlightEntryException.class, () -> {
            flightsService.createFlight(flightDTO);
        });
    }

    @Test
    public void displayOnlineBoard_flightsWithin24Hours_displayedCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24hours = now.plusHours(24);

        // Prepare test data
        Flight flight1 = new Flight("Kiev", "Destination1", now.plusHours(12), 100);
        Flight flight2 = new Flight("Kiev", "Destination2", next24hours.minusHours(1), 100);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        FlightDAO flightDAO = Mockito.mock(FlightDAO.class);
        Mockito.when(flightDAO.getAllFlights()).thenReturn(flights);

        FlightsService flightsService = new FlightsService(flightDAO);

        // Redirect System.out.println to a PrintStream so we can capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call method under test
        flightsService.displayOnlineBoard();

        // Verify output
        String expectedOutput = "Displaying flights within the next 24 hours :\n";
        expectedOutput += "Flight ID: " + flight2.getId() + "\n";
        expectedOutput += "Origin: Kiev\n";
        expectedOutput += "Destination: Destination2\n";
        expectedOutput += "Departure Time: " + flight2.getDepartureTime() + "\n";
        expectedOutput += "Available Seats: " + flight2.getAvailableSeats() + "\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
