import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingManager {
    private static ParkingManager instance;
    private final Map<VehicleType, ParkingSpot> spots;
    private final Map<String, Vehicle> parkedVehicles;

    private ParkingManager() {
        spots = new HashMap<>();
        parkedVehicles = new HashMap<>();
    }

    public static synchronized ParkingManager getInstance() {
        if (instance == null) {
            instance = new ParkingManager();
        }
        return instance;
    }

    public void addParkingSpot(VehicleType type, ParkingSpot spot) {
        spots.put(type, spot);
    }

    public int getTotalAvailableSpots() {
        return spots.values().stream()
                .mapToInt(ParkingSpot::getAvailableSpots)
                .sum();
    }

    public int getAvailableSpots(VehicleType type) {
        ParkingSpot spot = spots.get(type);
        return (spot != null) ? spot.getAvailableSpots() : 0;
    }

    public boolean parkVehicle(String plate, VehicleType type) {
        ParkingSpot spot = spots.get(type);
        if (spot != null && spot.enter(plate)) {
            parkedVehicles.put(plate, new Vehicle(plate, type));
            System.out.println(plate + " entered. Occupied: " +
                    spot.getOccupiedSpots() + "/" + spot.getCapacity());
            return true;
        }
        return false;
    }

    public double leaveVehicle(String plate, FeeStrategy feeStrategy) {
        Vehicle vehicle = parkedVehicles.get(plate);
        if (vehicle == null) return 0;

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(vehicle.getEntryTime(), now);
        double minutes = duration.toMinutes() + (duration.toSecondsPart() / 60.0);


        double feeAmount = feeStrategy.calculate(minutes);

        ParkingSpot spot = spots.get(vehicle.getType());
        if (spot != null) {
            spot.leave(plate);
            System.out.println(plate + " left. Occupied: " +
                    spot.getOccupiedSpots() + "/" + spot.getCapacity());
        }

        parkedVehicles.remove(plate);
        return feeAmount;
    }
}
