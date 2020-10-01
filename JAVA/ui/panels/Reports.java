package ui.panels;

import components.Control.SimulationController;
import components.Driving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * This class Displays the written reports on the screen
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Reports extends JMenu implements ActionListener {
    private SimulationController simulationController;
    private JMenuItem reports;
    private JFrame frame;
    private String path = "C:\\Users\\Tzlil Levi\\Desktop\\Java Projects\\homework4\\src\\reports.txt";
    private String line = "";

    /**
     * The constructor
     * @param simulationController - - Represent the Driving class
     */
    public Reports(SimulationController simulationController) {
        super("Reports");
        this.simulationController=simulationController;
        reports = new JMenuItem("Reports");
        this.add(reports);
        reports.addActionListener(this);
    }

    /**
     * If the user select reports at menu - A dialog will open showing all the reports that are in the file
     * @param e -The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String read;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            read = reader.readLine();
            while (read != null) {
                if(read !=null) {
                    line +=  read+"\n" ;
                }
                read = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException t) {
            System.out.println("Error: File not found.");

        } catch (IOException ee) {
            ee.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, line, "Reports", JOptionPane.PLAIN_MESSAGE);

    }


}
