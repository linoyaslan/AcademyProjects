package components;

/**
 * Class used to implement the design pattern: Builder
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class RoadEngineer {
    private MapBuilder mapBuilder;

    /**
     * constructor
     * @param mapBuilder - instance of MapBuilder
     */
    public RoadEngineer(MapBuilder mapBuilder){
        this.mapBuilder = mapBuilder;
    }

    /**
     * get function of Map
     * @return instance of Map
     */
    public Map getMap(){
        return this.mapBuilder.getMap();
    }

    /**
     *A function that creates intersections and roads, uses the interface: MapBuilder
     */
    public void constructMap(){
        this.mapBuilder.createJunctions();
        this.mapBuilder.createRoads();
    }
}
