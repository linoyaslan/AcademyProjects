package components;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * BigBrother is a class that represents a traffic controller, its show is single and accessed
 * This will be done through a static method of the class.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class BigBrother implements Observer {

    private static BigBrother bigBrother;
    private Mediator mediatorMoked;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static int numberOfReport =0;

    /**
     * The constructor of the class
     */
    private BigBrother(){
        System.out.println(" Creating the Bigbrother...");
        mediatorMoked = new Moked();
    }

    /**
     * A static method with which to create a single instance of the class
     * @return instance of BigBrother
     */
    public static BigBrother getBigBrother() {
        if (bigBrother == null) {
            synchronized (BigBrother.class) {
                if(bigBrother == null)
                bigBrother = new BigBrother();
            }
        }
        return bigBrother;
    }

    /**
     *An average vehicle speed computer from node to node and records to a file size report
     * And the speed is greater than the maximum legacy speed.
     * @param vehicle- instance of Vehicle
     */
    public void checkSpeed(Vehicle vehicle)  {
        if (vehicle.getVehicleSpeed()*10 > vehicle.getLastRoad().getMaxSpeed()) {
            numberOfReport++;
            String txt = "Time: "+formatter.format(new Date()) +" Number of report: " + numberOfReport+" Number of vehicle: "+ vehicle.getId();
            mediatorMoked.writeAReport(txt);
            vehicle.getDriver().getMyReports().add(txt);
        }
    }

    /**
     * Method that belongs to a design pattern: Listener
     * @param o - instance of vehicle - This is the object that Inspector will listen to
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        checkSpeed((Vehicle)o);
    }

    /**
     * get function
     * @return Mediator - instance of Moked
     */
    public Mediator getMoked() {
        return mediatorMoked;
    }
}
