/**
 * 
 */
package utilities;

import java.io.IOException;

/**
 * The interface of timer.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public interface Timer {
	public void incrementDrivingTime() throws InterruptedException, IOException;
	public  void resume(); /* The Vehicle class and the Traffic Lights class is realizing this method to do a notify to the threads */
}

//TODO Answer for bonus task 2: timer extends utilities, delete "implements utilities" from all classes.
