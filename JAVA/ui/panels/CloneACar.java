package ui.panels;

import components.Control.SimulationController;
import components.Driving;
import components.Vehicle;
import components.VehicleByWheels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This class Creates a new vehicle by replicating an existing vehicle
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class CloneACar extends JMenu implements ActionListener {
    private JSlider vehicleSlider;
    private SimulationController simulationController;
    private JMenuItem cloneACar;
    private JFrame frame;

    /**
     * An "Anonymous" ActionListener class - If the user is select the OK button
     * Here The "clone" method of Driving class is activate,After this click, a new vehicle is created
     */
    private ActionListener okActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = null;
            int idOfVehicle = vehicleSlider.getValue()-1;
            vehicle = (Vehicle) ((Driving)simulationController).getVehicles().get(idOfVehicle).clone();
            ((Driving)simulationController).getVehicles().add(vehicle);
            ((Driving)simulationController).getAllTimedElements().add(vehicle);
            ((Driving)simulationController).getOutputCanvas().addDrawable(vehicle);
            new Thread(vehicle).start();
            ((Driving)simulationController).refreshCanvas();
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
     * @param simulationController - - Represent the Driving class
     */
    public CloneACar(SimulationController simulationController) {
        super("Clone a car");
        this.simulationController=simulationController;
        cloneACar = new JMenuItem("Clone a car");
        cloneACar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.add(cloneACar);
        cloneACar.addActionListener(this);
    }

    /**
     * If the user select clone a car at menu - A window opens for him to choose the vehicle id he would like to replicate
     * @param e -The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        frame = new JFrame("Clone a car");
        JPanel mainPanel = new JPanel();
        frame.setPreferredSize(new Dimension(600, 300));
        frame.pack();
        JPanel idOfVehicle = new JPanel();
        JLabel labeljunction = new JLabel("Select the id of vehicle you want to copy");
        labeljunction.setHorizontalAlignment(JLabel.CENTER);
        idOfVehicle.add(labeljunction);
        idOfVehicle.setLayout(new GridLayout(2,1));
        vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0, ((Driving)simulationController).getVehicles().size(), 0);
        vehicleSlider.setPreferredSize(new Dimension(580,50));
        vehicleSlider.setMajorTickSpacing(1);
        vehicleSlider.setPaintTicks(true);
        vehicleSlider.setPaintLabels(true);
        idOfVehicle.add(vehicleSlider);
        //frame.add(idOfVehicle);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2));
        JButton ok = new JButton("OK");
        ok.setBackground(Color.GRAY);
        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.GRAY);
        buttons.setPreferredSize(new Dimension(600,30));
        buttons.add(ok,BorderLayout.SOUTH);
        buttons.add(cancel,BorderLayout.SOUTH);

        mainPanel.add(idOfVehicle);
        mainPanel.add(buttons);
        frame.add(mainPanel);
        frame.setVisible(true);
        ok.addActionListener(okActionListener);
        cancel.addActionListener(cancelActionListener);

    }

    /**
     * Close method - for close frame
     */
    private void close() {
        frame.setVisible(false);
    }

}
