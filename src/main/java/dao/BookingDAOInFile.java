package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.BookingController;
import model.Booking;
import model.BookingType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingDAOInFile implements BookingDAO{

    public static final String RESOURCE = "src/main/java/dao/resource/";
    private static final String DATABASE = RESOURCE + "databaseForBookings.txt";

    private final ObjectMapper objectMapper;

    private final  Path path = Paths.get(DATABASE);



    public BookingDAOInFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveBooking(Booking booking) {
        File file = new File(DATABASE);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(objectMapper.writeValueAsString(booking));
            bw.newLine(); // Add a newline after each flight to separate them
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBooking(Booking booking) {
        getAllBookings().remove(booking);
    }

    @Override
    public Collection<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    Booking booking = objectMapper.readValue(line, Booking.class);
                    bookings.add(booking);
                } catch (IOException e) {
                    System.err.println("Error reading flight from file: " + line);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading flights from file");
            e.printStackTrace();
        }
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