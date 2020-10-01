package components;

import components.Control.SimulationController;
import utilities.VehicleType;


import java.util.Enumeration;
/**
 * This class belongs to the realization of the design pattern: Abstract Factory,
 * Represents 2 wheeled vehicles
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class twoWheelsFactory implements VehicleByWheels {

    private SimulationController simulationController;
    //private Road currentLocation;
    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public twoWheelsFactory(SimulationController simulationController){
        this.simulationController = simulationController;
        //this.currentLocation = currentLocation;

    }


    /**
     *The function accepts property and returns the appropriate composition accordingly
     * @param property - property of vehicle
     * @return A car instance of the property we received
     */
    public Vehicle getVehicle(String property) {
        Vehicle vehicle = null;
        if(property.equals("fast")){
            vehicle = new Vehicle(VehicleType.valueOf("motorcycle"),simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("motorcycle"));

        }
        else if(property.equals("slow")){
            vehicle = new Vehicle(VehicleType.valueOf("bicycle"),simulationController);
            //vehicle.setVehicleType(VehicleType.valueOf("bicycle"));

        }
        return vehicle;
    }
}
