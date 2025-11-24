package Fee;

public class HourlyFeeStrategy implements FeeStrategy {
    private final double ratePerHour;

    public HourlyFeeStrategy(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculate(double minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative");
        }
        else {
            double hours = Math.ceil(minutes / 60.0);
            return hours * ratePerHour;
        }
    }
    @Override
    public String toString() {
        return "Hourly Fee: " + ratePerHour + "/hour";
    }
}
