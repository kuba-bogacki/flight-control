package org.flight.control.utils;

import org.flight.control.model.Flight;
import org.flight.control.store.AirportService;
import org.flight.control.store.FlightService;

import java.util.List;

public class Simulation {

    private final AirportService airportService;
    private final FlightService flightService;

    public Simulation(AirportService airportService, FlightService flightService) {
        this.airportService = airportService;
        this.flightService = flightService;
    }

    public List<Flight> simulateFlights() {
        List<Flight> flightList = flightService.getAllNotCancelFlights();
        for (Flight flight : flightList) {
            if (!flight.isHasStarted()) {
                flightService.startPlane(flight.getPlane());
                airportService.removePlaneFromAirport(flight);
                flightService.flyPlane(flight.getPlane());
            } else if (flight.isHasStarted() && !flight.isHasLanded()) {
                int planeRow = flight.getPlane().getSquare().getCoordinates().getRow();
                int planeCol = flight.getPlane().getSquare().getCoordinates().getCol();
                int localizationRow = flight.getFlightTo().getLocalization().getCoordinates().getRow();
                int localizationCol = flight.getFlightTo().getLocalization().getCoordinates().getCol();
                if ((planeRow == localizationRow) && (planeCol == localizationCol)) {
                    flightService.landPlane(flight.getPlane());
                    airportService.addPlaneToAirport(flight);
                } else {
                    flightService.flyPlane(flight.getPlane());
                }
            }
        }
        return flightList;
    }

    public boolean checkIfAllPlaneAreLanded(List<Flight> flightList) {
        boolean someFlyIsOn = false;
        for (Flight flight : flightList) {
            if (!flight.isHasLanded()) {
                someFlyIsOn = true;
                break;
            }
        }
        return someFlyIsOn;
    }

    public void printAllOngoingFlights() {
        String uuidPrefix = "Flight UUID = ";
        String departureDateTimePrefix = ", Departure Date & Time = ";
        String arrivalDateTimePrefix = ", Arrival Date & Time = ";
        List<Flight> ongoingFlights = flightService.getAllOngoingFlights();
        if (ongoingFlights.size() > 0) {
            System.out.println(" ".repeat(55) + ".....Ongoing flights.....");
            printLoopMethod(uuidPrefix, departureDateTimePrefix, arrivalDateTimePrefix, ongoingFlights);
        }
    }

    public void printAllLandedFlights() {
        String uuidPrefix = "Flight UUID = ";
        String departureDateTimePrefix = ", Departure Date & Time = ";
        String arrivalDateTimePrefix = ", Arrival Date & Time = ";
        List<Flight> landedFlights = flightService.getAllLandedFlights();
        if (landedFlights.size() > 0) {
            System.out.println(" ".repeat(55) + ".....Ended flights.....");
            printLoopMethod(uuidPrefix, departureDateTimePrefix, arrivalDateTimePrefix, landedFlights);
        }
    }

    public void printAllCancelFlights() {
        String uuidPrefix = "Flight UUID = ";
        String departureDateTimePrefix = ", Departure Date & Time = ";
        String arrivalDateTimePrefix = ", Arrival Date & Time = ";
        List<Flight> canceledFlights = flightService.getAllCanceledFlights();
        if (canceledFlights.size() > 0) {
            System.out.println(" ".repeat(55) + ".....Canceled flights.....");
            printLoopMethod(uuidPrefix, departureDateTimePrefix, arrivalDateTimePrefix, canceledFlights);
        }
    }

    private void printLoopMethod(String uuidPrefix, String departureDateTimePrefix,
                                 String arrivalDateTimePrefix, List<Flight> flights) {
        String line = "=".repeat(uuidPrefix.length() + flights.get(0).getId().toString().length() +
                departureDateTimePrefix.length() + flights.get(0).getDepartureDateTime().toString().substring(0, 10).length() +
                flights.get(0).getDepartureDateTime().toString().substring(12, 16).length() + arrivalDateTimePrefix.length() +
                flights.get(0).getArrivalDateTime().toString().substring(0, 10).length() +
                flights.get(0).getArrivalDateTime().toString().substring(12, 16).length() + 10);
        System.out.println(line);
        for (Flight flight : flights) {
            String formattedDepartureDateTime = flight.getDepartureDateTime().toString().substring(0, 10) + " -> " +
                    flight.getDepartureDateTime().toString().substring(12, 16);
            String formattedArrivalDateTime = flight.getArrivalDateTime().toString().substring(0, 10) + " -> " +
                    flight.getArrivalDateTime().toString().substring(12, 16);
            System.out.println("|" + uuidPrefix + flight.getId() + departureDateTimePrefix + formattedDepartureDateTime +
                    arrivalDateTimePrefix + formattedArrivalDateTime + "|");
        }
        System.out.println(line);
    }
}
