package org.flight.control.model.airplane;

import org.flight.control.model.Square;

import java.util.UUID;

public abstract class Plane {

    private final UUID id;
    private final String name;
    private final long weight;
    private final int tankCapacity;
    private final int cruisingSpeed;
    private Square square;

    public Plane(String name, long weight, int tankCapacity, int cruisingSpeed, Square square) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.weight = weight;
        this.tankCapacity = tankCapacity;
        this.cruisingSpeed = cruisingSpeed;
        this.square = square;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getWeight() {
        return weight;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public int getCruisingSpeed() {
        return cruisingSpeed;
    }

    public Square getSquare() {
        return square;
    }

    public abstract int getPeopleOrTonnage();

    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return "Plane = [id = " + id + ", name = " + name + ", weight = " + weight +
                ", tankCapacity = " + tankCapacity + ", cruisingSpeed = " + cruisingSpeed + "]";
    }
}
