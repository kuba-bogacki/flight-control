package org.flight.control.store;

import org.flight.control.exception.AirportUnavailable;
import org.flight.control.exception.PlaneNotFoundException;
import org.flight.control.model.Airport;
import org.flight.control.model.Flight;
import org.flight.control.model.airplane.Plane;

import java.time.LocalDateTime;
import java.util.*;

public class FlightServiceImplementation implements FlightService {

    private final List<Flight> flightList = new ArrayList<>();
    private final AirportService airportService;

    public FlightServiceImplementation(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public void createFlights() throws PlaneNotFoundException, AirportUnavailable {
        flightList.add(
            new Flight(airportService.getAirportByCity("Denver").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Denver").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Cairo").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                180, LocalDateTime.of(2017, 2, 13, 13, 5)));
        flightList.add(
            new Flight(airportService.getAirportByCity("Dallas").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Dallas").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Shanghai").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                130, LocalDateTime.of(2017, 2, 13, 18, 12)));
        flightList.add(
            new Flight(airportService.getAirportByCity("Houston").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Houston").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Beijing").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                550, LocalDateTime.of(2017, 2, 13, 8, 36)));
        flightList.add(
            new Flight(airportService.getAirportByCity("Beijing").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Beijing").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Denver").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                50, LocalDateTime.of(2017, 2, 13, 15, 15)));
        flightList.add(
            new Flight(airportService.getAirportByCity("Shanghai").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Shanghai").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Orlando").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                8000, LocalDateTime.of(2017, 2, 13, 15, 15)));
        flightList.add(
            new Flight(airportService.getAirportByCity("Bangkok").stream().map(Airport::getPlaneFromAirport)
                .findFirst().orElseThrow(() -> new PlaneNotFoundException("No plane on airport")),
                airportService.getAirportByCity("Bangkok").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                airportService.getAirportByCity("Washington").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")),
                9000, LocalDateTime.of(2017, 2, 13, 15, 15)));
    }

    @Override
    public Optional<Flight> getFlightById(UUID flightId) {
        return flightList.stream()
                .filter(flight -> flight.getId().equals(flightId))
                .findFirst();
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightList.stream()
            .toList();
    }

    @Override
    public List<Flight> getAllOngoingFlights() {
        return flightList.stream()
            .filter(Flight::isHasStarted)
            .filter(flight -> !flight.isHasLanded())
            .toList();
    }

    @Override
    public List<Flight> getAllLandedFlights() {
        return flightList.stream()
            .filter(Flight::isHasStarted)
            .filter(Flight::isHasLanded)
            .toList();
    }

    @Override
    public List<Flight> getAllNotCancelFlights() {
        return flightList.stream()
                .filter(flight -> !flight.isCancel())
                .toList();
    }

    @Override
    public List<Flight> getAllCanceledFlights() {
        return flightList.stream()
            .filter(Flight::isCancel)
            .toList();
    }

    @Override
    public void startPlane(Plane plane) {
        flightList.stream()
            .filter(flight -> flight.getPlane().equals(plane))
            .findFirst()
            .ifPresent(e -> e.takeOfThePlane(true));
    }

    @Override
    public void flyPlane(Plane plane) {
        flightList.stream()
            .filter(flight -> flight.getPlane().equals(plane))
            .findFirst()
            .ifPresent(e -> e.flyToAirport(plane));
    }

    @Override
    public void landPlane(Plane plane) {
        flightList.stream()
            .filter(flight -> flight.getPlane().equals(plane))
            .findFirst()
            .ifPresent(e -> e.landThePlane(true));
    }

    @Override
    public void checkIfPlaneCanFly() {
        flightList.stream()
            .filter(flight -> flight.getPassengerOrTonnage() > flight.getPlane().getPeopleOrTonnage())
            .forEach(flight -> flight.cancelFlight(true));
    }
}
