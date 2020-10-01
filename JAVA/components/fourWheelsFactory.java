package components;

import components.Control.SimulationController;
import utilities.VehicleType;

/**
 * This class belongs to the realization of the design pattern: Abstract Factory,
 * Represents 4 wheeled vehicles
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class fourWheelsFactory implements VehicleByWheels{
    private SimulationController simulationController;
    //private Road currentLocation;

    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public fourWheelsFactory(SimulationController simulationController){
        this.simulationController = simulationController;
       // this.currentLocation =currentLocation;
    }

    /**
     *The function accepts property and returns the appropriate composition accordingly
     * @param property - property of vehicle
     * @return A car instance of the property we received
     */
    public Vehicle getVehicle(String property) {
        Vehicle vehicle = null;
        if(property.equals("private")){
            vehicle = new Vehicle(VehicleType.valueOf("car"),simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("car"));
        }
        else if(property.equals("public")){
            vehicle = new Vehicle(VehicleType.valueOf("bus"),simulationController);
           // vehicle.setVehicleType(VehicleType.valueOf("bus"));
        }
        else if(property.equals("work")){
            vehicle = new Vehicle(VehicleType.valueOf("truck"), simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("truck"));
        }
        return vehicle;
    }
}
