import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import console.ConsoleApp;
import controller.BookingController;
import controller.FlightController;
import dao.BookingDAOInFile;
import dao.FlightDAO;
import dao.FlightDAOInFile;
import dto.FlightDTO;
import service.BookingService;
import service.FlightsService;

public class App {
    public static void main(String[] args) {
        FlightDAOInFile flightDAOInFile = new FlightDAOInFile(new ObjectMapper().registerModule(new JavaTimeModule()));
        FlightsService flightsService = new FlightsService(flightDAOInFile);
        FlightController flightController = new FlightController(flightsService);

        BookingDAOInFile bookingDAOInFile = new BookingDAOInFile(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingService bookingService = new BookingService(bookingDAOInFile);
        BookingController bookingController = new BookingController(bookingService);

        ConsoleApp consoleApp = new ConsoleApp(flightController, bookingController);
        consoleApp.start();
    }
}
