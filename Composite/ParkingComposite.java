package Composite;

import Singelton.ParkingComponent;

import java.util.ArrayList;
import java.util.List;

public class ParkingComposite extends ParkingComponent {
    private List<ParkingComponent> components = new ArrayList<>();

    public ParkingComposite(String name) {
        super(name);
    }

    public void add(ParkingComponent component) {
        components.add(component);
    }

    public void remove(ParkingComponent component) {
        components.remove(component);
    }


    @Override
    public int getAvailableSpots() {
        int available = 0;
        for (ParkingComponent comp : components) {
            available += comp.getAvailableSpots();
        }
        return available;
    }

    @Override
    public void printStatus(String indent) {
        System.out.println(indent + name + " status:");
        for (ParkingComponent comp : components) {
            comp.printStatus(indent + "  ");
        }
    }
}
