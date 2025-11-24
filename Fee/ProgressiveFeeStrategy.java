package Fee;

class ProgressiveFeeStrategy implements FeeStrategy {
    @Override
    public double calculate(double minutes) {
        if (minutes <= 60) {
            return 1.0;
        }
        return 1.0 + (minutes - 60) * 0.05;
    }
}
