package service;

import dao.BookingDAO;
import dao.User;
import model.Booking;
import dto.BookingDTO;
import model.BookingType;
import service.exceptions.BookingNotFoundException;
import service.exceptions.DuplicateBookingEntryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class BookingService {
    private BookingDAO bookingDAO;
    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public BookingDTO bookReservation(int flightID){
        Booking booking = new Booking(flightID);

        System.out.println("Please enter number of the passengers:");

        int numOfPeople = new Scanner(System.in).nextInt();

        for(int i = 0; i < numOfPeople; i++){

            System.out.println("Enter the full name of passenger " + (i+1) + " :");

            String fullName = new Scanner(System.in).nextLine();

            User user = new User(fullName);

            booking.getPassengers().add(user);


        }

        if(bookingDAO.existById(booking)){
            throw new DuplicateBookingEntryException("Booking already exists!");
        }

        booking.setBookingType(BookingType.CONFIRMED);

        bookingDAO.saveBooking(booking);

        return new BookingDTO(booking.getFlightID(), booking.getPassengers(), booking.getBookingType());

    }
    public Collection<Booking> cancelTheBooking(){

        System.out.println("Enter the booking ID to cancel it:");
        int bookingid = new Scanner(System.in).nextInt();

        Booking bookingByID = bookingDAO.getBookingByID(bookingid);

        if(bookingByID == null){
            throw new BookingNotFoundException("Booking not found!");
        }

        bookingByID.setBookingType(BookingType.CANCELLED);

        bookingByID.getPassengers().clear();

        System.out.println("Booking cancelled");

        return bookingDAO.getAllBookings();

    }
    public Collection<Booking> mybookings(){
        System.out.println("Enter your name:");
        String name = new Scanner(System.in).nextLine();
        List<Booking> myBookings = new ArrayList<>();
        User user = new User(name);
        for(Booking booking: bookingDAO.getAllBookings()){
            for(User u : booking.getPassengers()){
                if(u.getFullName().equalsIgnoreCase(user.getFullName())){
                    myBookings.add(booking);
                }
            }
        }

        return myBookings;
    }
}