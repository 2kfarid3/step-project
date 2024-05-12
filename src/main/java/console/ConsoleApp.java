package console;

public class ConsoleApp {
    private FlightsController flightsController;

    private BookingController bookingController;

    public ConsoleApp(FlightsController flightsController, BookingController bookingController) {
        this.flightsController = flightsController;
        this.bookingController = bookingController;
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getUserChoice();
            exit = userHandleInput(choice);
        }
    }

    private void printMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Online-board");
        System.out.println("2. Show the flight info");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel the booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        int choice = new Scanner(System.in).nextInt();
        System.out.println();
        return choice;
    }

    private boolean userHandleInput(int choice) {
        switch (choice) {
            case 1:
                // Implement Online-board functionality
                flightsController.displayOnlineBoard();
                System.out.println();
                break;
            case 2:
                // Implement Show the flight info functionality
                FlightDTO flightDTO = flightsController.showFlightInfo();
                System.out.println("Here are the details of the flight you wanted");
                System.out.println(flightDTO);
                System.out.println();
                break;
            case 3:
                // Implement Search and book a flight functionality
                BookingDTO bookingDTO = bookingController.bookReservation(flightsController.searchFlight());
                System.out.println();
                break;
            case 4:
                // Implement Cancel the booking functionality
                System.out.println(bookingController.cancelTheBooking());
                System.out.println();
                break;
            case 5:
                // Implement My flights functionality
                Collection<Booking> mybookings = bookingController.mybookings();
                System.out.println(mybookings);
                System.out.println();
                break;
            case 6:
                System.out.println("Exiting the application...");
                return true; // Exit the loop
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
        return false; // Continue the loop
    }
}
