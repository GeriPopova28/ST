import Fee.FeeStrategy;
import Fee.ProgressiveFeeStrategy;
import Manager.ParkingManager;
import Spot.SingleSpot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        ParkingManager parking = ParkingManager.getInstance();
        FeeStrategy feeStrategy = new ProgressiveFeeStrategy();

        parking.addParkingSpot(VehicleType.CAR, new SingleSpot(5));
        parking.addParkingSpot(VehicleType.MOTORCYCLE, new SingleSpot(3));
        parking.addParkingSpot(VehicleType.TRUCK, new SingleSpot(2));

        ConcurrentHashMap<String, VehicleRecord> records = new ConcurrentHashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.submit(() -> {
            System.out.println("--- Initial parking status ---");
            System.out.println("Total available spots: " + parking.getTotalAvailableSpots());
            System.out.println("CAR: " + parking.getAvailableSpots(VehicleType.CAR));
            System.out.println("MOTORCYCLE: " + parking.getAvailableSpots(VehicleType.MOTORCYCLE));
            System.out.println("TRUCK: " + parking.getAvailableSpots(VehicleType.TRUCK));
            System.out.println("-------------------------------");
        });

        // Паралелно влизане на превозни средства
        executor.submit(() -> {
            String plate = "CA1234AB";
            if (parking.parkVehicle(plate, VehicleType.CAR)) {
                VehicleRecord rec = new VehicleRecord("CAR", LocalDateTime.now());
                records.put(plate, rec);
            }
        });

        executor.submit(() -> {
            String plate = "CB5678KM";
            if (parking.parkVehicle(plate, VehicleType.MOTORCYCLE)) {
                VehicleRecord rec = new VehicleRecord("MOTORCYCLE", LocalDateTime.now());
                records.put(plate, rec);
            }
        });

        executor.submit(() -> {
            String plate = "CC9999TT";
            if (parking.parkVehicle(plate, VehicleType.TRUCK)) {
                VehicleRecord rec = new VehicleRecord("TRUCK", LocalDateTime.now());
                records.put(plate, rec);
            }
        });

        executor.submit(() -> processExit(parking, "CA1234AB", feeStrategy, records, 65_000));
        executor.submit(() -> processExit(parking, "CB5678KM", feeStrategy, records, 70_000));
        executor.submit(() -> processExit(parking, "CC9999TT", feeStrategy, records, 75_000));

        executor.shutdown();
    }

    private static void processExit(ParkingManager parking, String plate, FeeStrategy fee,
                                    ConcurrentHashMap<String, VehicleRecord> records, long sleepMillis) {
        try { Thread.sleep(sleepMillis); } catch (InterruptedException e) { e.printStackTrace(); }
        double feeAmount = parking.leaveVehicle(plate, fee);

        VehicleRecord rec = records.get(plate);
        if (rec != null) {
            rec.setExitTime(LocalDateTime.now());
            rec.setStayMinutes(Duration.between(rec.getEntryTime(), rec.getExitTime()).toMinutes());
            rec.setFee(feeAmount);
        }

        System.out.println("Vehicle " + plate + " left. Fee: $" + feeAmount);
        System.out.println("Stay duration: " + (rec != null ? rec.getStayMinutes() : "N/A") + " minutes");
        System.out.println("----------------------------------");
    }
}
