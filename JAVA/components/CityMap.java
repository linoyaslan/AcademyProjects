package components;

import components.Control.SimulationController;

import java.security.Signature;

/**
 *This class creates a deflection map, an urban map will contain 12 intersected intersections with roads, no trucks will be created on this map,
 * and a speed increase that can only exceed 30% on speed
 * Average of the vehicle.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class CityMap implements MapBuilder{
    private Map map;
    private SimulationController simulationController;
    private JFactory jFactory;

    /**
     * The constructor of the class
     * @param simulationController -Represent the Driving class
     */
    public CityMap(SimulationController simulationController){
        this.simulationController = simulationController;
        jFactory = new JFactory(simulationController);
        map = new Map(simulationController);
    }

    /**
     * A function that creates junction using a design pattern Factory Method
     */
    @Override
    public void createJunctions() {
        for (int i = 0; i <12 ; i++){
            LightedJunction junc = (LightedJunction) jFactory.getJunction("city");
            map.getJunctions().add(junc);
            map.getLights().add(junc.getLights());
        }
    }

    /**
     * function the create roads
     */
    @Override
    public void createRoads() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == j) {

                    continue;
                }
                map.getRoads().add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
            }
        }
    }

    /**
     * Get function
     * @return instance of Map
     */
    @Override
    public Map getMap() {
        return this.map;
    }
}
