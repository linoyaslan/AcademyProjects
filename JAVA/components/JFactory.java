package components;


import components.Control.SimulationController;

import java.util.Random;

/**
 * The contributing department for the implementation of the design patten: Factory Method
 *@author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class JFactory {

    private SimulationController simulationController;

    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public JFactory(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    /**
     * A function that returns a new instance of a node by the type of map we received
     * @param x - type of map
     * @return new instance of junction
     */
    public Junction getJunction(String x) {
        Random rand = new Random();
        boolean flag;
        if (x == "city") {
            return new LightedJunction(simulationController);
        }
        if (x == "country") {
            flag = rand.nextBoolean();
            if (flag) {
                return new LightedJunction(simulationController);
            } else {
                return new Junction();
            }
        }
        return null;
    }


}

