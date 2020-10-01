/**
 * 
 */
package components;
import components.Control.SimulationController;

import java.util.ArrayList;

/** Represents the traffic lights with random choice of road that receives a green light
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public class RandomTrafficLights extends TrafficLights {
	
	/**Constructor
	 * @param roads
	 */
	public RandomTrafficLights(ArrayList<Road> roads, SimulationController simulationController) {
		super(roads,simulationController);
	}

	@Override
	public void changeIndex() {
		
		this.setGreenLightIndex((getRandomInt(1,200))%this.getRoads().size());
		
	}
	
	@Override
	public String toString() {
		return new String("Random "+super.toString());
	}
	
	

}
