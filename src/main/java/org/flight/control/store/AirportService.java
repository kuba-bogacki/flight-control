package org.flight.control.store;

import org.flight.control.exception.AirportUnavailable;
import org.flight.control.model.Airport;
import org.flight.control.model.Flight;
import org.flight.control.model.airplane.Plane;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    void createAirports();
    void putPlanesOnAirports() throws AirportUnavailable;
    List<Airport> getAllAirports();
    List<Plane> getAllPlanes();
    Optional<Airport> getAirportByCity(String city);
    void removePlaneFromAirport(Flight flight);
    void addPlaneToAirport(Flight flight);
}
