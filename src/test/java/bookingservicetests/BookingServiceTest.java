package bookingservicetests;

import dao.BookingDAO;
import dao.User;
import dto.BookingDTO;
import model.Booking;
import model.BookingType;
import org.junit.Test;
import org.mockito.Mockito;
import service.BookingService;
import service.exceptions.BookingNotFoundException;
import service.exceptions.DuplicateBookingEntryException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BookingServiceTest {
    @Test
    public void testBookReservation() {
        // Mocking dependencies
        BookingDAO bookingDAO = Mockito.mock(BookingDAO.class);
        BookingDTO expectedBookingDTO = new BookingDTO(123, Arrays.asList(new User("John Doe")), BookingType.CONFIRMED);
        when(bookingDAO.existById(Mockito.any())).thenReturn(false);

        BookingService bookingService = new BookingService(bookingDAO);

        // Mocking user input
        System.setIn(new java.io.ByteArrayInputStream("1\nJohn Doe\n".getBytes()));

        // Call the method
        BookingDTO actualBookingDTO = bookingService.bookReservation(123);

        // Verify the result
        assertEquals(expectedBookingDTO, actualBookingDTO);
    }

    @Test
    public void testBookReservationWithExistingBooking() {
        // Mocking dependencies
        BookingDAO bookingDAO = Mockito.mock(BookingDAO.class);
        when(bookingDAO.existById(Mockito.any())).thenReturn(true);

        BookingService bookingService = new BookingService(bookingDAO);

        // Mocking user input
        System.setIn(new java.io.ByteArrayInputStream("1\nJohn Doe\n".getBytes()));

        // Call the method and expect an exception
        assertThrows(DuplicateBookingEntryException.class, () -> {
            bookingService.bookReservation(123);
        });
    }

    @Test
    public void cancelTheBooking_bookingNotFound_throwsBookingNotFoundException() {
        BookingDAO bookingDAO = Mockito.mock(BookingDAO.class);
        Mockito.when(bookingDAO.getBookingByID(Mockito.anyInt())).thenReturn(null);

        BookingService bookingService = new BookingService(bookingDAO);

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.cancelTheBooking();
        });
    }

    @Test
    public void cancelTheBooking_bookingFound_cancelsBooking() {
        BookingDAO bookingDAO = Mockito.mock(BookingDAO.class);
        Booking booking = new Booking();
        Mockito.when(bookingDAO.getBookingByID(Mockito.anyInt())).thenReturn(booking);

        BookingService bookingService = new BookingService(bookingDAO);

        Collection<Booking> bookings = bookingService.cancelTheBooking();

        assertTrue(booking.getBookingType() == BookingType.CANCELLED);
        assertTrue(booking.getPassengers().isEmpty());
    }

    @Test
    public void mybookings_returnsCorrectBookings() {
        BookingDAO bookingDAO = Mockito.mock(BookingDAO.class);
        BookingService bookingService = new BookingService(bookingDAO);

        // Prepare test data
        Booking booking1 = new Booking();
        User user1 = new User("John Doe");
        booking1.getPassengers().add(user1);

        Booking booking2 = new Booking();
        User user2 = new User("Alice Smith");
        booking2.getPassengers().add(user2);

        Mockito.when(bookingDAO.getAllBookings()).thenReturn(List.of(booking1, booking2));

        // Test mybookings method
        Collection<Booking> myBookings = bookingService.mybookings();

        assertEquals(1, myBookings.size());
        assertTrue(myBookings.contains(booking1));
    }
}
