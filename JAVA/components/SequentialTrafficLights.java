package components;

import components.Control.SimulationController;

import java.util.ArrayList;

/** Represents traffic lights with sequential choice of road that receives green light.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public class SequentialTrafficLights extends TrafficLights {
	public final int increment=1;
	
	/**Constructor
	 * @param roads list of roads that are controlled by those lights
	 */
	public SequentialTrafficLights(ArrayList<Road> roads,  SimulationController simulationController) {
		super(roads,simulationController);

		//successMessage(this.toString());
	}

	@Override
	public void changeIndex() {
		
		this.setGreenLightIndex((this.getGreenLightIndex()+increment)%this.getRoads().size());//increment index
			}
	@Override
	public String toString() {
		return new String("Sequential "+super.toString());
	}

	/**
	 * @return the increment
	 */
	public int getIncrement() {
		return increment;
	}
	
	
}
