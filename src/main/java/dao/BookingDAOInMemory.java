package dao;

import model.Booking;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDAOInMemory implements BookingDAO {

    private static List<Booking> bookings;
    @Override
    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Collection<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public Booking getBookingByID(int id) {
        return getAllBookings().stream().filter(x->x.getBookingID() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public boolean existById(Booking booking) {
        for (Booking booking1: getAllBookings()){
            if(booking1.getBookingID() == booking.getBookingID()){
                return true;
            }
        }

        return false;
    }


}