package ui.panels;

import components.Control.SimulationController;
import components.Control.UiController;
import components.Driving;
import components.Map;
import components.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class builds a map by type : City, Country
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class BuildAMap extends JMenu implements ActionListener {

    private JMenuItem cityBuilder, countryBuilder;
    private SimulationController simulationController;
    private UiController uiController;
    private JSlider vehicleSlider;
    private JFrame frame;
    private int numberOfVehicles;

    /**
     * An "Anonymous" ActionListener class - If the user is select the OK button
     * Here The "createCity" method of Driving class is activate
     */
    private ActionListener okActionListenerCity = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberOfVehicles =vehicleSlider.getValue();
            simulationController.createCity(numberOfVehicles);
            ((Driving)simulationController).setStop(false);
            close();

        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the OK button
     * Here The "createCountry" method of Driving class is activate
     */
    private ActionListener okActionListenerCountry = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberOfVehicles = vehicleSlider.getValue();
            simulationController.createCountry(numberOfVehicles);
            ((Driving)simulationController).setStop(false);
            close();

        }
    };

    /**
     * An "Anonymous" ActionListener class - If the user is select the cancel button
     * Here The "close" method of this class is activate
     */
    private ActionListener cancelActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    };

    /**
     * The constructor
     * @param uiController - Represent the Driving class
     * @param simulationController - - Represent the Driving class
     */
    public BuildAMap(UiController uiController, SimulationController simulationController) {
        super("Build a map");
        this.uiController = uiController;
        this.simulationController = simulationController;
        cityBuilder = new JMenuItem("cityBuilder");
        countryBuilder = new JMenuItem("countryBuilder");

        this.add(cityBuilder);
        this.add(countryBuilder);

        cityBuilder.addActionListener(this);
        countryBuilder.addActionListener(this);
    }

    /**
     * If the user select build a map at menu - He can choose whether to build an City map or an Country map
     * @param e -The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        simulationController.stop();
        if (e.getSource().equals(cityBuilder)) {
            frame = new JFrame("City builder");
            JPanel mainPanel = new JPanel();
            frame.setPreferredSize(new Dimension(600, 300));
            frame.pack();
            JPanel numOfVehicle = new JPanel();
            JLabel labeljunction = new JLabel("Select number of vehicles");
            labeljunction.setHorizontalAlignment(JLabel.CENTER);
            numOfVehicle.add(labeljunction);
            numOfVehicle.setLayout(new GridLayout(2,1));
            vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0,50, 25);
            vehicleSlider.setPreferredSize(new Dimension(580,50));
            vehicleSlider.setMajorTickSpacing(5);
            vehicleSlider.setMinorTickSpacing(1);
            vehicleSlider.setPaintTicks(true);
            vehicleSlider.setPaintLabels(true);
            numOfVehicle.add(vehicleSlider);


            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(1,2));
            JButton ok = new JButton("OK");
            ok.setBackground(Color.GRAY);
            JButton cancel = new JButton("Cancel");
            cancel.setBackground(Color.GRAY);
            buttons.setPreferredSize(new Dimension(600,30));
            buttons.add(ok,BorderLayout.SOUTH);
            buttons.add(cancel,BorderLayout.SOUTH);

            mainPanel.add(numOfVehicle);
            mainPanel.add(buttons);
            frame.add(mainPanel);
            frame.setVisible(true);
            ok.addActionListener(okActionListenerCity);
            cancel.addActionListener(cancelActionListener);


        } else if (e.getSource().equals(countryBuilder)) {
            frame = new JFrame("Country builder");
            JPanel mainPanel = new JPanel();
            frame.setPreferredSize(new Dimension(600, 300));
            frame.pack();
            JPanel numOfVehicle = new JPanel();
            JLabel labelVehicles = new JLabel("Select number of vehicles");
            labelVehicles.setHorizontalAlignment(JLabel.CENTER);
            numOfVehicle.add(labelVehicles);
            numOfVehicle.setLayout(new GridLayout(2,1));
            vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0,50, 25);
            vehicleSlider.setPreferredSize(new Dimension(580,50));
            vehicleSlider.setMajorTickSpacing(5);
            vehicleSlider.setMinorTickSpacing(1);
            vehicleSlider.setPaintTicks(true);
            vehicleSlider.setPaintLabels(true);
            numOfVehicle.add(vehicleSlider);


            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(1,2));
            JButton ok = new JButton("OK");
            ok.setBackground(Color.GRAY);
            JButton cancel = new JButton("Cancel");
            cancel.setBackground(Color.GRAY);
            buttons.setPreferredSize(new Dimension(600,30));
            buttons.add(ok,BorderLayout.SOUTH);
            buttons.add(cancel,BorderLayout.SOUTH);

            mainPanel.add(numOfVehicle);
            mainPanel.add(buttons);
            frame.add(mainPanel);
            frame.setVisible(true);
            ok.addActionListener(okActionListenerCountry);
            cancel.addActionListener(cancelActionListener);
        }
    }


    /**
     * Close method - for close frame
     */
    private void close() {
        frame.setVisible(false);
    }
}

