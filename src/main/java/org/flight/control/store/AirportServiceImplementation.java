package org.flight.control.store;

import org.flight.control.exception.AirportUnavailable;
import org.flight.control.model.*;
import org.flight.control.model.airplane.Cargo;
import org.flight.control.model.airplane.Passenger;
import org.flight.control.model.airplane.Plane;
import org.flight.control.model.types.PlaneType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirportServiceImplementation implements AirportService {

    List<Airport> airportList = new ArrayList<>();

    @Override
    public void createAirports() {
        airportList.add(new Airport("King Fahd International", "Saudi Arabia", new Square(new Coordinates(6, 24), PlaneType.AIRPORT)));
        airportList.add(new Airport("Denver International Port", "Denver", new Square(new Coordinates(2, 4), PlaneType.AIRPORT)));
        airportList.add(new Airport("Fort Worth International", "Dallas", new Square(new Coordinates(6, 4), PlaneType.AIRPORT)));
        airportList.add(new Airport("Orlando International Port", "Orlando", new Square(new Coordinates(6, 12), PlaneType.AIRPORT)));
        airportList.add(new Airport("Washington Dulles International", "Washington", new Square(new Coordinates(4, 10), PlaneType.AIRPORT)));
        airportList.add(new Airport("Beijing Daxing International", "Beijing", new Square(new Coordinates(4, 36), PlaneType.AIRPORT)));
        airportList.add(new Airport("George Bush Intercontinental", "Houston", new Square(new Coordinates(9, 8), PlaneType.AIRPORT)));
        airportList.add(new Airport("Shanghai Pudong International", "Shanghai", new Square(new Coordinates(6, 36), PlaneType.AIRPORT)));
        airportList.add(new Airport("Cairo International Port", "Cairo", new Square(new Coordinates(8, 20), PlaneType.AIRPORT)));
        airportList.add(new Airport("Suvarnabhumi International Port", "Bangkok", new Square(new Coordinates(10, 32), PlaneType.AIRPORT)));
    }

    @Override
    public void putPlanesOnAirports() throws AirportUnavailable {
        Plane BOEING_737 = new Passenger("Boeing 737", 41000, 20000, 840, new Square(new Coordinates(
            this.getAirportByCity("Denver").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Denver").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.PASSENGER), 186);
        Plane AIRBUS_320 = new Passenger("Airbus 320", 73000, 23860, 828, new Square(new Coordinates(
            this.getAirportByCity("Dallas").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Dallas").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.PASSENGER), 150);
        Plane AIRBUS_380 = new Passenger("Airbus 380", 575000, 32000, 1040, new Square(new Coordinates(
            this.getAirportByCity("Houston").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Houston").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.PASSENGER), 600);
        Plane BOEING_C32 = new Passenger("Boeing C32", 99790, 32000, 850, new Square(new Coordinates(
            this.getAirportByCity("Beijing").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Beijing").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.PASSENGER), 30);
        Plane BOEING_C17 = new Cargo("Boeing C17", 128000, 147000, 700, new Square(new Coordinates(
            this.getAirportByCity("Shanghai").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Shanghai").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.CARGO), 77519);
        Plane AN_N22 = new Cargo("An-N22", 118727, 127000, 608, new Square(new Coordinates(
            this.getAirportByCity("Bangkok").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getRow(),
            this.getAirportByCity("Bangkok").orElseThrow(() -> new AirportUnavailable("Airport is unavailable")).getLocalization().getCoordinates().getCol()),
            PlaneType.CARGO), 96000);
        airportList.stream().filter(airport -> airport.getCity().equals("Denver")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(BOEING_737));
        airportList.stream().filter(airport -> airport.getCity().equals("Dallas")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(AIRBUS_320));
        airportList.stream().filter(airport -> airport.getCity().equals("Houston")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(AIRBUS_380));
        airportList.stream().filter(airport -> airport.getCity().equals("Beijing")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(BOEING_C32));
        airportList.stream().filter(airport -> airport.getCity().equals("Shanghai")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(BOEING_C17));
        airportList.stream().filter(airport -> airport.getCity().equals("Bangkok")).findFirst().ifPresent(airport -> airport.addPlaneOnAirport(AN_N22));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportList.stream().toList();
    }

    @Override
    public List<Plane> getAllPlanes() {
        return airportList.stream()
            .flatMap(planes -> planes.getPlanesFromAirport().stream())
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Airport> getAirportByCity(String city) {
        return airportList.stream()
            .filter(airport -> airport.getCity().equals(city))
            .findFirst();
    }

    @Override
    public void removePlaneFromAirport(Flight flight) {
        airportList.stream()
            .filter(airport -> airport.getCity().equals(flight.getFlightFrom().getCity()))
            .findFirst()
            .ifPresent(Airport::removePlaneFromAirport);
    }

    @Override
    public void addPlaneToAirport(Flight flight) {
        airportList.stream()
            .filter(airport -> airport.getCity().equals(flight.getFlightTo().getCity()))
            .findFirst()
            .ifPresent(airport -> airport.addPlaneOnAirport(flight.getPlane()));
    }
}
