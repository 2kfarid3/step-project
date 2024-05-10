package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Flight;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDAOInFile implements FlightDAO{

    public static final String RESOURCE =  "src/main/java/dao/resource/";

    private static final String DATABASE = RESOURCE + "databaseForFlights.txt";

    private final Path path = Paths.get(DATABASE);

    private final ObjectMapper objectMapper;

    public FlightDAOInFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveFlight(Flight flight) {


        File file = new File(DATABASE);

//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
//            Files.write(path, objectMapper.writeValueAsBytes(flight));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));) {
            bw.write(flight.toString() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight getFlightByID(int id) {
        return getAllFlights().stream().filter(x->x.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE));){
            String str = "";

            while((str = br.readLine()) != null){
                String [] parts;
                parts = str.split(",");


                int id = Integer.parseInt(parts[0].substring(parts[0].indexOf("=") + 1).trim());
                String origin = parts[1].substring(parts[1].indexOf("=") + 1).trim();
                String destination = parts[2].substring(parts[2].indexOf("=") + 1).trim();
                LocalDateTime dateTime = LocalDateTime.parse(parts[3].substring(parts[3].indexOf("=") + 1).trim());
                int availableSeats = Integer.parseInt(parts[4].substring(parts[4].indexOf("=") + 1, parts[4].length() - 1).trim());

                flights.add(new Flight(origin, destination, dateTime, availableSeats));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        try {
//            byte[] jsonData = Files.readAllBytes(path);
//            Flight flight = objectMapper.readValue(jsonData, Flight.class);
//            flights.add(flight);
//        } catch (IOException e) {
//            System.err.println("Error reading bookings from file");
//            e.printStackTrace();
//        }

        return flights;




    }

    @Override
    public void removeFlight(Flight flight) {

    }
}
