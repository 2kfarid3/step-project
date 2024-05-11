package step_project.service;

import step_project.dao.Passenger;
import step_project.model.dto.BookingDto;
import step_project.model.enumm.BookingEnum;

import java.util.Set;

public interface BookingService {
    BookingDto bookFlight(BookingDto bookingDto);
}
