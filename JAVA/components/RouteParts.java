/**
 * 
 */
package components;

import utilities.Utilities;

import java.io.IOException;


/**
 * Represent a Route part - A junction or a road.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public interface RouteParts extends Utilities {

	/**Calculates the estimated time that will take for a car to pass a route part
	 * @param obj
	 * @return
	 */
	public double calcEstimatedTime(Object obj);
	
	/**finds the next possible route part
	 * @param vehicle
	 * @return
	 */
	public RouteParts findNextPart(Vehicle vehicle);
	
	
	/**
	 * @param vehicle
	 */
	public void checkIn (Vehicle vehicle) ;
	
	/**
	 * @param vehicle
	 */
	public void checkOut (Vehicle vehicle);
	
	/**
	 * @param other
	 * @return
	 */
	public boolean equals(Object other);
	
	/**
	 * @param vehicle
	 */
	public void stayOnCurrentPart(Vehicle vehicle);
	
	/**
	 * @param vehicle
	 * @return
	 */
	public boolean canLeave(Vehicle vehicle);
	
}


