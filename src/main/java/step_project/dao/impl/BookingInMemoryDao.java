package step_project.dao.impl;

import step_project.dao.Booking;
import step_project.dao.BookingDao;

import java.util.HashSet;
import java.util.Set;

public class BookingInMemoryDao extends BookingDao {
    private static final Set<Booking> BOOKINGS = new HashSet<>();
    @Override
    public Booking save(Booking booking) {
        BOOKINGS.add(booking);
        return booking;
    }
}
