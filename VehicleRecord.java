import java.time.LocalDateTime;

public class VehicleRecord {
    private String type;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double stayMinutes;
    private double fee;

    public VehicleRecord(String type, LocalDateTime entryTime) {
        this.type = type;
        this.entryTime = entryTime;
    }


    public String getType() {
        return type;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getStayMinutes() {
        return stayMinutes;
    }

    public void setStayMinutes(double stayMinutes) {
        this.stayMinutes = stayMinutes;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
