package org.flight.control.model;

import org.flight.control.model.airplane.Plane;

import java.util.Stack;
import java.util.UUID;

public class Airport {

    private final UUID id;
    private final String name;
    private final String city;
    private final Square localization;
    private final Stack<Plane> planesOnAirport = new Stack<>();

    public Airport(String name, String city, Square localization) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.city = city;
        this.localization = localization;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Square getLocalization() {
        return localization;
    }

    public Stack<Plane> getPlanesFromAirport() {
        return planesOnAirport;
    }

    public Plane getPlaneFromAirport() {
        return planesOnAirport.peek();
    }

    public void addPlaneOnAirport(Plane plane) {
        this.planesOnAirport.add(plane);
    }

    public void removePlaneFromAirport() {
        this.planesOnAirport.pop();
    }

    @Override
    public String toString() {
        return "Airport = [id = " + id + ", name = " + name + ", city = " + city +
                ", localization = " + localization + ", planesOnAirport = " + planesOnAirport + "]";
    }
}
