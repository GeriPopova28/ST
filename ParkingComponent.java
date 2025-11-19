public abstract class ParkingComponent {
    protected String name;

    public ParkingComponent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public abstract int getAvailableSpots();
    public abstract void printStatus(String indent);
    public void add(ParkingComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(ParkingComponent component) {
        throw new UnsupportedOperationException();
    }

}
