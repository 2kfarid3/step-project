package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Flight;

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

public class FlightDAOInFile implements FlightDAO{

    public static final String RESOURCE =  "src/main/java/dao/resource/";

    private static final String DATABASE = RESOURCE + "databaseForFlights.txt";

    private final Path path = Paths.get(DATABASE);

    private final ObjectMapper objectMapper;



    public FlightDAOInFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public Flight getFlightById(int id) {
        return getAllFlights().stream().filter(x->x.getId() == id).collect(Collectors.toList()).get(0);
    }
    @Override
    public void deleteFlight(Flight flight) {
        getAllFlights().remove(flight);
    }
    public void saveFlight(Flight flight) {
        File file = new File(DATABASE);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(objectMapper.writeValueAsString(flight));
            bw.newLine(); // Add a newline after each flight to separate them
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Collection<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    Flight flight = objectMapper.readValue(line, Flight.class);
                    flights.add(flight);
                } catch (IOException e) {
                    System.err.println("Error reading flight from file: " + line);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading flights from file");
            e.printStackTrace();
        }
        return flights;
    }
    @Override
    public boolean existById(Flight flight) {
        for(Flight f:getAllFlights()){
            if(f.getId() == flight.getId()){
                return true;
            }
        }

        return false;
    }
}
