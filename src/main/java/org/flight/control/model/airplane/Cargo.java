package org.flight.control.model.airplane;

import org.flight.control.model.Square;

public class Cargo extends Plane {

    private final int tonnage;

    public Cargo(String name, long weight, int tankCapacity, int cruisingSpeed, Square square, int tonnage) {
        super(name, weight, tankCapacity, cruisingSpeed, square);
        this.tonnage = tonnage;
    }

    public int getTonnage() {
        return tonnage;
    }

    @Override
    public int getPeopleOrTonnage() {
        return tonnage;
    }

    @Override
    public String toString() {
        return "Cargo = [tonnage = " + tonnage + "]";
    }
}
