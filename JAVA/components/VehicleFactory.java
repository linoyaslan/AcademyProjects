package components;

import components.Control.SimulationController;
/**
 * Class used to implement a design pattern: Abstract Factory
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class VehicleFactory {

    private SimulationController simulationController;
   // private Road currentLocation;

    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public VehicleFactory(SimulationController simulationController){
       // this.currentLocation = currentLocation;
        this.simulationController=simulationController;
    }

    /**
     * The function will return factories of transport vehicles by wheels
     * @param numberOfWheels - number of wheels
     * @return new Factory
     */
    public  VehicleByWheels getFactory(int numberOfWheels){
        if(numberOfWheels == 2){
            return (VehicleByWheels) new twoWheelsFactory(simulationController);
        }
        else if (numberOfWheels == 4){
            return (VehicleByWheels)new fourWheelsFactory(simulationController);
        }
        else if(numberOfWheels == 10){
            return (VehicleByWheels)new tenWheelsFactory(simulationController);
        }
        return null;
    }
}
