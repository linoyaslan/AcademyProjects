package ui;

import ui.panels.RoadSimulatorPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the GUI system
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class RoadSimulatorFrame extends JFrame {

    /**
     * The constructor
     * @throws HeadlessException - Throw an exception
     */
    public RoadSimulatorFrame() throws HeadlessException {
        super("Road system");
        this.setContentPane(new RoadSimulatorPanel());
        this.pack();
    }

    /**
     * The main - Here the programmer play the GUI system
     * @param args
     */
    public static void main(String[] args) {
        RoadSimulatorFrame roadSimulatorFrame = new RoadSimulatorFrame();
        roadSimulatorFrame.setVisible(true);
        roadSimulatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
