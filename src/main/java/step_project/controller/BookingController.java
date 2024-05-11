package step_project.controller;

import step_project.dao.Booking;
import step_project.dao.Passenger;
import step_project.model.dto.BookingDto;
import step_project.model.enumm.BookingEnum;
import step_project.service.BookingService;
import step_project.service.BookingServiceImpl;

import java.util.Set;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingDto bookFlight(BookingDto bookingDto) {
        //to do checks
        return bookingService.bookFlight(bookingDto);
    }


}
