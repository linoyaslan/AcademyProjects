package components;

/**
 * Interface used to implement a design pattern: Builder
 */
public interface MapBuilder {
    public void createJunctions(); //A function that creates junction
    public void createRoads(); //A function that creates roads
    public Map getMap(); // A function return Map
}
