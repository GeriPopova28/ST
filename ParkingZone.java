import java.util.HashMap;
import java.util.Map;

class ParkingZone extends ParkingSpot {
    private Map<String, ParkingSpot> spots = new HashMap<>();

    public ParkingZone() {
        super(0);
    }

    public void addSpot(String type, ParkingSpot spot) {
        spots.put(type, spot);
    }

    @Override
    public boolean enter(String plate) {
        return false;
    }

    @Override
    public boolean leave(String plate) {
        return false;
    }

    @Override
    public int getAvailableSpots() {
        return spots.values().stream().mapToInt(ParkingSpot::getAvailableSpots).sum();
    }

    public int getAvailableSpotsByType(String type) {
        ParkingSpot spot = spots.get(type);
        if (spot != null) return spot.getAvailableSpots();
        return 0;
    }

    public boolean enterByType(String plate, String type) {
        ParkingSpot spot = spots.get(type);
        if (spot != null) return spot.enter(plate);
        return false;
    }

    public boolean leaveByType(String plate, String type) {
        ParkingSpot spot = spots.get(type);
        if (spot != null) return spot.leave(plate);
        return false;
    }
}
