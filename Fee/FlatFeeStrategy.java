package Fee;

public class FlatFeeStrategy implements FeeStrategy {
    private final double flatRate;

    public FlatFeeStrategy(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculate(double minutes) {
        return flatRate; // фиксирана такса, независимо от престоя
    }
    @Override
    public String toString() {
        return "Flat Fee: " + flatRate;
    }
}
