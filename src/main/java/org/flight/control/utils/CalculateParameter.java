package org.flight.control.utils;

import org.flight.control.model.Airport;
import org.flight.control.model.airplane.Plane;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class CalculateParameter {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static int calculateFlightDistance(Airport flightFrom, Airport flightTo) {
        int result = 1000;
        if (flightFrom.getLocalization().getCoordinates().getRow() == flightTo.getLocalization().getCoordinates().getRow()) {
            result *= Math.abs(flightFrom.getLocalization().getCoordinates().getCol() - flightTo.getLocalization().getCoordinates().getCol());
        } else if (flightFrom.getLocalization().getCoordinates().getCol() == flightTo.getLocalization().getCoordinates().getCol()) {
            result *= Math.abs(flightFrom.getLocalization().getCoordinates().getRow() - flightTo.getLocalization().getCoordinates().getRow());
        } else {
            double a2 = Math.pow(Math.abs(flightFrom.getLocalization().getCoordinates().getRow() - flightTo.getLocalization().getCoordinates().getRow()), 2);
            double b2 = Math.pow(Math.abs(flightFrom.getLocalization().getCoordinates().getCol() - flightTo.getLocalization().getCoordinates().getCol()), 2);
            double c2 = a2 + b2;
            result *= (int) Math.sqrt(c2);
        }
        return result;
    }

    public static LocalDateTime calculateFlightArrival(LocalDateTime departureDateTime, int distance, int cruisingSpeed) {
        double decimalHoursAndMinutes = (double) distance / (double) cruisingSpeed;
        String stringValueOfDecimalHoursAndMinutes = decimalFormat.format(decimalHoursAndMinutes);
        String[] stringValueOfDecimalHoursAndMinutesArray = stringValueOfDecimalHoursAndMinutes.split(",");
        int hours = Integer.parseInt(stringValueOfDecimalHoursAndMinutesArray[0]);
        int minutes = (Integer.parseInt(stringValueOfDecimalHoursAndMinutesArray[1]) * 60) / 100;
        return departureDateTime.plusHours(hours).plusMinutes(minutes);
    }

    public static void movePlaneOnMap(Plane plane, Airport flightTo) {
        int planeCoordinatesRow = plane.getSquare().getCoordinates().getRow();
        int planeCoordinatesCol = plane.getSquare().getCoordinates().getCol();
        int destinyCoordinatesRow = flightTo.getLocalization().getCoordinates().getRow();
        int destinyCoordinatesCol = flightTo.getLocalization().getCoordinates().getCol();

        if ((planeCoordinatesRow == destinyCoordinatesRow) && (planeCoordinatesCol < destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol + 1);
        } else if ((planeCoordinatesRow == destinyCoordinatesRow) && (planeCoordinatesCol > destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol - 1);
        } else if ((planeCoordinatesCol == destinyCoordinatesCol) && (planeCoordinatesRow < destinyCoordinatesRow)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow + 1);
        } else if ((planeCoordinatesCol == destinyCoordinatesCol) && (planeCoordinatesRow > destinyCoordinatesRow)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow - 1);
        } else if ((planeCoordinatesRow < destinyCoordinatesRow) && (planeCoordinatesCol < destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow + 1);
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol + 1);
        } else if ((planeCoordinatesRow > destinyCoordinatesRow) && (planeCoordinatesCol > destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow - 1);
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol - 1);
        } else if ((planeCoordinatesRow > destinyCoordinatesRow) && (planeCoordinatesCol < destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow - 1);
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol + 1);
        } else if ((planeCoordinatesRow < destinyCoordinatesRow) && (planeCoordinatesCol > destinyCoordinatesCol)) {
            plane.getSquare().getCoordinates().setRow(planeCoordinatesRow + 1);
            plane.getSquare().getCoordinates().setCol(planeCoordinatesCol - 1);
        }
    }
}
