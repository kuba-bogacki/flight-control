package org.flight.control.model;

import org.flight.control.store.AirportService;
import org.flight.control.store.FlightService;
import org.flight.control.model.types.PlaneType;

import java.util.List;

public class Map {

    private final Square[][] map;
    private final AirportService airportService;
    private final FlightService flightService;

    public Map(AirportService airportService, FlightService flightService) {
        this.map = new Square[20][40];
        this.airportService = airportService;
        this.flightService = flightService;
    }

    public Square[][] getMap() {
        return map;
    }

    public void updateGlobeMap() {
        int height = this.map.length;
        int width = this.map[0].length;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                this.map[row][col] = new Square(new Coordinates(row, col), PlaneType.EMPTY);
            }
        }
    }

    public void putAirportsOnMap() {
        List<Airport> airportsList = airportService.getAllAirports();
        for (Airport airport : airportsList) {
            Square square = airport.getLocalization();
            this.map[square.getCoordinates().getRow()][square.getCoordinates().getCol()] = square;
        }
    }

    public void putPlanesOnMap() {
        List<Flight> flightList = flightService.getAllFlights();
        for (Flight flight : flightList) {
            Square square = flight.getPlane().getSquare();
            if (!this.map[square.getCoordinates().getRow()][square.getCoordinates().getCol()].getPlaneType().getPlaneStyle().equals("\uD83D\uDD4B-")) {
                this.map[square.getCoordinates().getRow()][square.getCoordinates().getCol()] = square;
            }
        }
    }

    public void printGlobeMap() {
        int height = this.map.length;
        int width = this.map[0].length;
        System.out.println("In the meantime...");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(this.map[row][col].getPlaneType().getPlaneStyle());
            }
            System.out.println();
        }
    }
}
