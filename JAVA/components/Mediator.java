package components;

import java.util.ArrayList;

/**
 * Interface used to implement a design pattern: Mediator
 */
public interface Mediator {
    void writeAReport(String txt); //A function used to write to a file
    void readReport(Driver driver, String txt);//A function used to read from a file
    String getPath();//A function that returns the file path
    ArrayList<String> getReports();//function that return ArrayList of reports
}
