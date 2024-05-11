package step_project.service;

import step_project.dao.Booking;
import step_project.dao.BookingDao;
import step_project.dao.Passenger;
import step_project.model.dto.BookingDto;
import step_project.model.enumm.BookingEnum;

import java.util.Set;

public class BookingServiceImpl implements BookingService{
    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }


    @Override
    public BookingDto bookFlight(BookingDto bookingDto) {
        Booking booking = new Booking(
                bookingDto.getPassengers(),
                bookingDto.getFlightId(),
                BookingEnum.CONFIRMED,
                (int) (Math.random() * 10000)
        );
        Booking savedBooking = bookingDao.save(booking);
        return new BookingDto(
                savedBooking.getPassengers(),            //Booking or BookingDto
                savedBooking.getFlightId(),
                savedBooking.getBookingId()
        );
    }
}
