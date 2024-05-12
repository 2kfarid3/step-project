package controller;

import model.Booking;
import dto.BookingDTO;
import service.BookingService;

import java.util.Collection;

public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingDTO bookReservation(int flightID){
        return bookingService.bookReservation(flightID);
    }

    public Collection<Booking> cancelTheBooking() {
        return  bookingService.cancelTheBooking();
    }

    public Collection<Booking> mybookings(){
        return bookingService.mybookings();
    }
}