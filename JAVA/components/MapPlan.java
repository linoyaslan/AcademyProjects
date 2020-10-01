package components;

import java.util.ArrayList;

/**
 *  * Interface used to implement a design pattern: Builder
 */
public interface MapPlan {
    void setJunctions(ArrayList<Junction> junctions); // function set of juntion
    void setRoads(ArrayList<Road> roads);// function set of road
}
