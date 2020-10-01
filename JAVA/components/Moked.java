package components;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A department representing a report center, this is the department whose role is to link the inspector with the driver.
 * Class used to implement a design pattern:Mediator
 * In addition to this class there is a design pattern:DCL
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Moked implements Mediator {
    private ArrayList<String> reports = new ArrayList<String>();
    private static BufferedWriter writer;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String path = "C:\\Users\\Tzlil Levi\\Desktop\\Java Projects\\homework4\\src\\reports.txt";

    /**
     * constructor
     */
    public Moked() {

    }

    /**
     *Writing function with the help of the inspector writes a report to the file
     * @param txt - string of report
     */
    public void writeAReport(String txt) {
        readWriteLock.writeLock().lock();
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(path, true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write(txt);
            reports.add(txt);
            writer.close();

        } catch (FileNotFoundException t) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     *A read function with the help of the driver reads a report from the file
     * @param driver - driver of vehicle
     * @param txt - string of report
     */
    public void readReport(Driver driver, String txt) {
        readWriteLock.readLock().lock();
        if (driver.readReports(txt)) {
            reports.remove(txt);
            System.out.println("Reports that removes is : " + txt);
        }
        readWriteLock.readLock().unlock();
    }

    /**
     * Ger function of path
     * @return function that return the path of file
     */
    public String getPath() {
        return path;
    }

    /**
     * Get function of reports
     * @return function the return the Arratlist of reports
     */
    public ArrayList<String> getReports() {
        return reports;
    }


}
