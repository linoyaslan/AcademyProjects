package components;

import components.Control.SimulationController;
import utilities.VehicleType;
/**
 * This class belongs to the realization of the design pattern: Abstract Factory,
 * Represents 10 wheeled vehicles
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class tenWheelsFactory implements VehicleByWheels{
    private SimulationController simulationController;
    //private Road currentLocation;

    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public tenWheelsFactory(SimulationController simulationController){
        this.simulationController = simulationController;
        //this.currentLocation =currentLocation;
    }

    /**
     *The function accepts property and returns the appropriate composition accordingly
     * @param property - property of vehicle
     * @return A car instance of the property we received
     */
    public Vehicle getVehicle(String property) {
        Vehicle vehicle = null;
        if(property.equals("public")){
            vehicle = new Vehicle(VehicleType.valueOf("tram"),simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("tram"));

        }
        else if(property.equals("work")){
            vehicle = new Vehicle(VehicleType.valueOf("semitrailer"),simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("semitrailer"));

        }
        return vehicle;
    }
}
