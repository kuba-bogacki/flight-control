package org.flight.control.model.airplane;

import org.flight.control.model.Square;

public class Passenger extends Plane {

    private final int maxPassengers;

    public Passenger(String name, long weight, int tankCapacity, int cruisingSpeed, Square square, int maxPassengers) {
        super(name, weight, tankCapacity, cruisingSpeed, square);
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    @Override
    public int getPeopleOrTonnage() {
        return maxPassengers;
    }

    @Override
    public String toString() {
        return "Passenger = [maxPassengers = " + maxPassengers + "]";
    }
}
