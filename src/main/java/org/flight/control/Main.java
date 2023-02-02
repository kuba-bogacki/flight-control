package org.flight.control;

import org.flight.control.exception.AirportUnavailable;
import org.flight.control.exception.PlaneNotFoundException;
import org.flight.control.model.Flight;
import org.flight.control.model.Map;
import org.flight.control.store.*;
import org.flight.control.utils.Simulation;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final AirportService airportService = new AirportServiceImplementation();
    private static final FlightService flightService = new FlightServiceImplementation(airportService);
    private static boolean SOME_FLY_IS_ON = true;

    public static void main(String[] args) throws PlaneNotFoundException, AirportUnavailable, InterruptedException {
        airportService.createAirports();
        airportService.putPlanesOnAirports();
        flightService.createFlights();
        flightService.checkIfPlaneCanFly();
        Map map = new Map(airportService, flightService);
        Simulation simulation = new Simulation(airportService, flightService);
        map.putAirportsOnMap();
        simulation.printAllCancelFlights();

        while (SOME_FLY_IS_ON) {
            map.updateGlobeMap();
            List<Flight> flightList = simulation.simulateFlights();
            SOME_FLY_IS_ON = simulation.checkIfAllPlaneAreLanded(flightList);
            map.putPlanesOnMap();
            map.putAirportsOnMap();
            Thread.sleep(1000);
            map.printGlobeMap();
            simulation.printAllOngoingFlights();
            simulation.printAllLandedFlights();
        }
    }
}
