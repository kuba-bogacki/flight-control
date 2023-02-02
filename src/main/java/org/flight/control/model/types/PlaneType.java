package org.flight.control.model.types;

public enum PlaneType {
    CARGO("\uD83D\uDEEB-"),
    PASSENGER("\uD83D\uDEEB-"),
    AIRPORT("\uD83D\uDD4B-"),
    EMPTY("---");

    String planeStyle;

    PlaneType(String planeStyle) {
        this.planeStyle = planeStyle;
    }

    public String getPlaneStyle() {
        return planeStyle;
    }

    @Override
    public String toString() {
        return "PlaneType = [planeStyle = " + planeStyle + "]";
    }
}
