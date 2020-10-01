package components;

import components.Control.SimulationController;

import java.util.Random;

/**
 *This class creates a deflection map, a CountryMap map containing 6 junction only some of them are hinted at, with not all junctions connected together.
 * There will be no bicycle and tram on this map
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class CountryMap implements MapBuilder {
    private Map map;
    private SimulationController simulationController;
    private JFactory jFactory;

    /**
     * The constructor of the class
     * @param simulationController - Represent the Driving class
     */
    public CountryMap(SimulationController simulationController) {
        map = new Map(simulationController);
        this.simulationController = simulationController;
        jFactory = new JFactory(simulationController);
    }

    /**
     * A function that creates junction using a design pattern Factory Method
     */
    @Override
    public void createJunctions() {
        for (int i = 0; i <6 ; i++){
            Junction junc = jFactory.getJunction("country");
            map.getJunctions().add(junc);
            if(junc instanceof LightedJunction) {
                map.getLights().add(((LightedJunction)junc).getLights());
            }
        }
    }

    /**
     * create a roads
     */
    @Override
    public void createRoads() {
        Random rand = new Random();
        boolean flag;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == j) {

                    continue;
                }
                flag = rand.nextBoolean();
                if (flag) {
                    map.getRoads().add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
                }
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
