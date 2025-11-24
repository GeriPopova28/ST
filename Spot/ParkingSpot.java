package Spot;

import java.util.HashSet;
import java.util.Set;

public abstract class ParkingSpot {
    protected int capacity;
    protected Set<String> occupiedPlates = new HashSet<>();

    public ParkingSpot(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean enter(String plate) {
        if (occupiedPlates.size() < capacity) {
            occupiedPlates.add(plate);
            return true;
        }
        return false;
    }

    public synchronized boolean leave(String plate) {
        return occupiedPlates.remove(plate);
    }

    public int getAvailableSpots() {
        return capacity - occupiedPlates.size();
    }

    // 👇 тези два метода решават грешката ти:
    public int getOccupiedSpots() {
        return occupiedPlates.size();
    }

    public int getCapacity() {
        return capacity;
    }
}
