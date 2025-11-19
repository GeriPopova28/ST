import java.time.Duration;
import java.time.LocalDateTime;

class ParkingRecord {
    String licensePlate;
    String type;
    LocalDateTime entryTime;
    LocalDateTime exitTime;
    double fee;

    public ParkingRecord(String licensePlate, String type, LocalDateTime entryTime,
                         LocalDateTime exitTime, double fee) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
    }

    public long getDurationMinutes() {
        return Duration.between(entryTime, exitTime).toMinutes();
    }
}
