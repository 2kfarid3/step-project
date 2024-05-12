package dao;

import model.Booking;

import java.io.IOException;
import java.util.Collection;

public interface BookingDAO {
    void saveBooking(Booking booking);

    void deleteBooking(Booking booking);

    Collection<Booking> getAllBookings();

    Booking getBookingByID(int id);

    boolean existById(Booking booking);



}