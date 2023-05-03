package org.example;

public class ParkingSpace {
    private String spaceNumber;
    private boolean isOccupied;

    public ParkingSpace(String spaceNumber, boolean isOccupied) {
        this.spaceNumber = spaceNumber;
        this.isOccupied = isOccupied;
    }

    public String getSpaceNumber() {
        return spaceNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
