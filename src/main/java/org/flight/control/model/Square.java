package org.flight.control.model;

import org.flight.control.model.types.PlaneType;

public class Square {

    private Coordinates coordinates;
    private PlaneType planeType;

    public Square(Coordinates coordinates, PlaneType planeType) {
        this.coordinates = coordinates;
        this.planeType = planeType;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    @Override
    public String toString() {
        return "Square = [coordinates = " + coordinates + ", planeType = " + planeType + "]";
    }
}
