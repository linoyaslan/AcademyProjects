package ui.panels;

import components.Control.SimulationController;
import components.Driving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represent the buttons panel of the system.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class ButtonsBar extends JPanel {
    private final int width;
    private final int height;
    private Color background;
    private JButton createRoadSystem, start, resume, info, stop;
    private SimulationController simulationController;
    private JFrame owner;
    private boolean flag = false;

    /**
     * The constructor of the class
     * @param width- The width of the panel
     * @param height - The height of the panel
     * @param simulationController - Represent the Driving class
     */
    public ButtonsBar(int width, int height, SimulationController simulationController) {
        this.width = width;
        this.height = height;
        this.background = Color.GRAY;
        this.simulationController = simulationController;
        this.owner = (JFrame) SwingUtilities.windowForComponent(this);

        /* Initialize the buttons in the panel */
        createRoadSystem = new JButton("Create road system");
        start = new JButton("Start");
        stop = new JButton("Stop");
        resume = new JButton("Resume");
        info = new JButton("Info");

        this.setLayout(new GridLayout(1, 5));

        /* Add the buttons to the panel */
        this.add(createRoadSystem);
        this.add(start);
        this.add(stop);
        this.add(resume);
        this.add(info);

        /* Design of the buttons */
        createRoadSystem.setBackground(Color.GRAY);
        start.setBackground(Color.GRAY);
        stop.setBackground(Color.GRAY);
        resume.setBackground(Color.GRAY);
        info.setBackground(Color.GRAY);

        createRoadSystem.addActionListener(createRoadSystemListener);
        start.addActionListener(startActionListener);
        stop.addActionListener(stopActionListener);
        resume.addActionListener(resumeActionListener);
        info.addActionListener(infoActionListener);
    }

    /**
     * An "Anonymous" ActionListener class - If the user is select the createRoadSystem button
     */
    private ActionListener createRoadSystemListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Create road system")) {
                /* Create a dialog createRoadsSystem object */
                JDialog dialog = new CreateRoadsSystemFrame(owner, simulationController);
                ((Driving) simulationController).setFlagIsRunning(false);
                ((Driving) simulationController).setStop(false);
                dialog.setVisible(true);
            }
        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the start button
     */
    private ActionListener startActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            simulationController.start();
        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the stop button
     */
    private ActionListener stopActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            simulationController.stop();
        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the resume button
     */
    private ActionListener resumeActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            simulationController.resume();
        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the info button
     */
    private ActionListener infoActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            flag = !flag;
            simulationController.Info(flag);
        }
    };


}