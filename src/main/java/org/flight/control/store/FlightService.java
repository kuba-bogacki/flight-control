package org.flight.control.store;

import org.flight.control.exception.AirportUnavailable;
import org.flight.control.exception.PlaneNotFoundException;
import org.flight.control.model.Flight;
import org.flight.control.model.airplane.Plane;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlightService {
    void createFlights() throws PlaneNotFoundException, AirportUnavailable;
    Optional<Flight> getFlightById(UUID flightId);
    List<Flight> getAllFlights();
    List<Flight> getAllOngoingFlights();
    List<Flight> getAllLandedFlights();
    List<Flight> getAllNotCancelFlights();
    List<Flight> getAllCanceledFlights();
    void startPlane(Plane plane);
    void flyPlane(Plane plane);
    void landPlane(Plane plane);
    void checkIfPlaneCanFly();
}
