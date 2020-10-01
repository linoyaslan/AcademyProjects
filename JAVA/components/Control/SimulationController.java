/**
 * The controller of the system - How the simulation "behaves"
 * The Driving class is implements this interface.
 * @author - Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * @see - components.Driving
 */

package components.Control;


import components.Map;

import java.io.IOException;

public interface SimulationController {
    void create(int numOfJunctions, int numOfVehicle) ; /* The create function - Create a map with by select number of junctions and number of vehicles */
    void start(); /* The activate og the threads of the system (Vehicles, Traffic Lights and Driving */
    void stop(); /* If this function is activate, the threads will be wait (do a "stop") */
    void resume(); /* If  this function is activate, the threads will notify (" wake up ") */
    void Info(boolean flag); /* The information about the vehicles */
    void setFlagIsRunning(boolean value); /* a helping method. Set the falgIsRunning attribute of the Driving class */
    boolean getFlagIsRuning(); /* a helping method. Get the falgIsRunning attribute of the Driving class */
    boolean getStop(); /* a helping method. Get the stop attribute of the Driving class */
    void setStop(boolean value); /* a helping method. Set the stop attribute of the Driving class */
    void createCountry(int numOfVehicles);
    void createCity(int numOfVehicles);

}
