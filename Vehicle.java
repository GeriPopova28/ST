import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle {
    private String licensePlate;
    private VehicleType type;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.entryTime = LocalDateTime.now();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFee() {
        return fee;
    }

    public void exit(double ratePerMinute) {
        this.exitTime = LocalDateTime.now();
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        this.fee = minutes * ratePerMinute;
    }

    public int getOccupiedSpaces() {
        switch (type) {
            case MOTORCYCLE: return 1;
            case CAR: return 2;
            case TRUCK: return 3;
            default: return 1;
        }
    }
}

enum VehicleType {
    MOTORCYCLE, CAR, TRUCK
}
