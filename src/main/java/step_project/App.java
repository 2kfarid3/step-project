package step_project;

import step_project.controller.BookingController;
import step_project.dao.Booking;
import step_project.dao.BookingDao;
import step_project.dao.impl.BookingInMemoryDao;
import step_project.model.dto.BookingDto;
import step_project.service.BookingService;
import step_project.service.BookingServiceImpl;

public class App {
    public static void main(String[] args) {
        BookingDao bookingDao =
                new BookingInMemoryDao();

        BookingService bookingService = new BookingServiceImpl(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        bookingController.bookFlight(new BookingDto());
    }
}
