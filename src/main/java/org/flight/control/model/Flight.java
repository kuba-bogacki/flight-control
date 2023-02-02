package org.flight.control.model;

import org.flight.control.model.airplane.Plane;
import org.flight.control.utils.CalculateParameter;

import java.time.LocalDateTime;
import java.util.UUID;

public class Flight {

    private UUID id;
    private Plane plane;
    private Airport flightFrom;
    private Airport flightTo;
    private int distance;
    private int passengerOrTonnage;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private boolean hasStarted;
    private boolean hasLanded;
    private boolean isCancel;

    public Flight(Plane plane, Airport flightFrom, Airport flightTo, int passengerOrTonnage, LocalDateTime departureDateTime) {
        this.id = UUID.randomUUID();
        this.plane = plane;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.distance = calculateDistance();
        this.passengerOrTonnage = passengerOrTonnage;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = calculateArrivalDateTime();
        this.hasStarted = false;
        this.hasLanded = false;
        this.isCancel = false;
    }

    public UUID getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public Airport getFlightFrom() {
        return flightFrom;
    }

    public Airport getFlightTo() {
        return flightTo;
    }

    public int getDistance() {
        return distance;
    }

    public int getPassengerOrTonnage() {
        return passengerOrTonnage;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public boolean isHasLanded() {
        return hasLanded;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public int calculateDistance() {
        return CalculateParameter.calculateFlightDistance(this.flightFrom, this.flightTo);
    }

    public LocalDateTime calculateArrivalDateTime() {
        return CalculateParameter.calculateFlightArrival(this.departureDateTime, this.distance, this.plane.getCruisingSpeed());
    }

    public void flyToAirport(Plane plane) {
        CalculateParameter.movePlaneOnMap(plane, this.flightTo);
    }

    public void changePlaneDestiny(Airport flightTo) {
        this.flightTo = flightTo;
        this.distance = calculateDistance();
        this.arrivalDateTime = calculateArrivalDateTime();
    }

    public void takeOfThePlane(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public void landThePlane(boolean hasLanded) {
        this.hasLanded = hasLanded;
    }

    public void cancelFlight(boolean isCancel) {
        this.isCancel = isCancel;
    }

    @Override
    public String toString() {
        return "Flight = [id = " + id + ", plane = " + plane + ", flightFrom = " + flightFrom + ", flightTo = " + flightTo +
                ", departureDateTime = " + departureDateTime + ", arrivalDateTime = " + arrivalDateTime +
                ", distance = " + distance + ", hasStarted = " + hasStarted + ", hasLanded = " + hasLanded +
                ", isCancel = " + isCancel + "]";
    }
}
