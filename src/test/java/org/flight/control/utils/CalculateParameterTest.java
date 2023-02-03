package org.flight.control.utils;

import org.flight.control.model.Airport;
import org.flight.control.store.AirportService;
import org.flight.control.store.AirportServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculateParameterTest {

    @Mock
    Airport airportFrom;
    @Mock
    Airport airportTo;
    @Mock
    AirportService airportService;

    @BeforeEach
    public void setUp() {
        airportService = new AirportServiceImplementation();
        airportService.createAirports();
        airportFrom = airportService.getAirportByCity("Denver").orElseThrow();
        airportTo = airportService.getAirportByCity("Cairo").orElseThrow();
    }

    @Test
    public void calculateFlightDistanceEqualTest() {
        int expectedValue = CalculateParameter.calculateFlightDistance(airportFrom, airportTo);
        int actualValue = 17000;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void calculateFlightDistanceNotEqualTest() {
        int unexpectedValue = CalculateParameter.calculateFlightDistance(airportFrom, airportTo);
        int actualValue = 21216;
        assertNotEquals(unexpectedValue, actualValue);
    }

    @Test
    public void calculateFlightArrivalEqualTest() {
        LocalDateTime expectedValue =
            CalculateParameter.calculateFlightArrival(LocalDateTime.of(2023, 1, 3, 5, 20),
                17000, 840);
        LocalDateTime actualValue = LocalDateTime.of(2023, 1, 4, 1, 34);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void calculateFlightArrivalNotEqualTest() {
        LocalDateTime expectedValue =
                CalculateParameter.calculateFlightArrival(LocalDateTime.of(2023, 1, 3, 5, 20),
                        17000, 840);
        LocalDateTime actualValue = LocalDateTime.of(2023, 1, 5, 1, 34);
        assertNotEquals(expectedValue, actualValue);
    }
}
