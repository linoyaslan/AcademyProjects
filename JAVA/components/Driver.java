package components;

import ui.panels.File;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *A class representing a driver of a vehicle
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Driver {
    private ArrayList<String> myReports = new ArrayList<String>();
    private String readerBuffer;
    private int vehicleId;
    private Mediator mediatorMoked;

    /**
     * The constructor of the class
     * @param id - id of vehicle
     * @param mediatorMoked - instance of Mediator Which is actually Moked
     */
    public Driver(int id,Mediator mediatorMoked) {
        vehicleId = id;
        this.mediatorMoked=mediatorMoked;
    }

    /**
     * Get function
     * @return myReports - ArrayList of driver's reports
     */
    public ArrayList<String> getMyReports() {
        return myReports;
    }

    /**
     * A function that erases a report from a list of reports
     * @param txt - string of report
     */
    public void removeRepot(String txt) {
        myReports.remove(txt);
    }

    /**
     * Function comes driver reads the report received from the inspector from the file, the driver reads the report with an instance of Moked , There is a design pattern used: Madiator
     * @param txt -The report the driver needs to read
     * @return The function returns true if the driver was able to read the report
     */
    public boolean readReports(String txt) {
        boolean inRepords = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mediatorMoked.getPath()));
            String line = reader.readLine();
            while (line != null) {
                if (line.equalsIgnoreCase(txt)) {
                    myReports.remove(txt);
                    inRepords = true;
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException t) {
            System.out.println("Error: File not found.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inRepords;
    }
}
