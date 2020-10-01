/**
 * 
 */
package components;

import java.awt.*;
import java.util.ArrayList;

import components.Control.SimulationController;
import ui.Entities.Drawable;
import utilities.Timer;
import utilities.Utilities;

/** Represents traffic lights
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public abstract class TrafficLights implements Timer, Utilities, Runnable {
	private int id;
	private final int maxDelay=6;
	private final int minDelay=2;
	private boolean trafficLightsOn;
	private int greenLightIndex;
	private int delay;
	private int workingTime;
	private ArrayList<Road> roads; 
	private static int objectsCount=1;
	private SimulationController simulationController;

	
	/**Constructor
	 * @param roads
	 */
	public TrafficLights(ArrayList<Road> roads, SimulationController simulationController) {
		id=objectsCount++;
		trafficLightsOn=false;
		greenLightIndex=-1;
		delay=0;
		workingTime=0;
		this.roads=roads;
		this.simulationController=simulationController;

		
	}
	
	/**
	 * @return the trafficLightsOn
	 */
	public boolean getTrafficLightsOn() {
		return trafficLightsOn;
	}
	
	
	/**
	 * @param on
	 */
	public void setTrafficLightsOn(boolean on) {
		if (on) {
			if (roads.size()<1) {
				System.out.println(this + "Lights can not be turned on at junction with no entering roads");
				return;
			}
			trafficLightsOn=true;
			delay=getRandomInt(minDelay,maxDelay);
			System.out.println(this+ " turned ON, delay time: "+ delay);
			changeLights();
		}
		else {
			trafficLightsOn=false;
			delay=0;
			for(Road road: roads) {
				road.setGreenLight(false);
			}
		}
	}
	
	
	

	/**
	 * @return
	 */
	public int getGreenLightIndex() {
		return greenLightIndex;
	}
	
	/**
	 * @param index
	 */
	public void setGreenLightIndex(int index) {
		greenLightIndex=index;
	}
	
	/**
	 * @return
	 */
	public int getDelay() {
		return delay;
	}
	
	/**
	 * @param delay
	 */
	public void setDelay (int delay) {
		this.delay=delay;
	}
	
	/**
	 * @param time
	 */
	public void setWorkingTime(int time) {
		workingTime=time;
	}
	
	/**
	 * @return
	 */
	public int getWorkingTime() {
		return workingTime;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Road> getRoads(){
		return roads;
	}
	
	/**
	 * @param roads
	 */
	public void setRoads(ArrayList<Road> roads) {
		this.roads=roads;
	}
	
	@Override
	public void incrementDrivingTime() {
		if (trafficLightsOn) {
			workingTime++;
			if (workingTime%delay==0) {
				changeLights();
			}
			else {
				System.out.println("- on delay");
			}
		}
	}
	
	
	/**gives a green light to another road
	 * 
	 */
	public synchronized void changeLights() {
		if (!trafficLightsOn) {
			System.out.println("- Traffic lights are off and can't be changed");
		}
		else {
			for (Road road:roads) {
					road.setGreenLight(false);
			}
			changeIndex();
			this.getRoads().get(this.getGreenLightIndex()).setGreenLight(true);//set green light to the next road
			System.out.println("- "+ this.getRoads().get(this.getGreenLightIndex())+": green light.");//print message
			notifyAll();
			
		}
	}
	
	/**update the index of the next road that supposed to receive green light
	 * 
	 */
	public abstract void changeIndex();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false; 
	    if (getClass() != obj.getClass()) return false; 
	    if (! super.equals(obj)) return false;
	    TrafficLights other=(TrafficLights)obj;
	    if (this.delay!=other.delay||
	    	this.greenLightIndex!=other.greenLightIndex||
	    	this.roads!=other.roads||
	    	this.trafficLightsOn!=other.trafficLightsOn||
	    	this.workingTime!=other.workingTime
	    		
	    	) return false;
	    return true;
	    
	}
	
	@Override
	public String toString() {
		return new String("traffic lights "+ id);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the objectsCount
	 */
	public static int getObjectsCount() {
		return objectsCount;
	}

	/**
	 * @param objectsCount the objectsCount to set
	 */
	public static void setObjectsCount(int objectsCount) {
		TrafficLights.objectsCount = objectsCount;
	}

	/**
	 * @return the maxDelay
	 */
	public int getMaxDelay() {
		return maxDelay;
	}

	/**
	 * @return the minDelay
	 */
	public int getMinDelay() {
		return minDelay;
	}

	public synchronized void resume(){
		this.notify();
	}

	/**
	 * The run method of the trafficLights objects threads
	 */
	@Override
	public void run() {
		while(simulationController.getFlagIsRuning()) {
			try {
				Thread.sleep(delay*100);
				while (simulationController.getStop()){
					if(!(simulationController.getFlagIsRuning())){
						return;
					}
					synchronized (this){
						wait();
					}
				}
				incrementDrivingTime();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}


