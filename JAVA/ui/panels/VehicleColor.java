package ui.panels;

import components.Control.SimulationController;
import components.Control.UiController;
import components.Driving;
import components.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The menu of the vehicle color - change the color of the all vehicles by the selection of the user
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class VehicleColor extends JMenu implements ActionListener {
    private JMenuItem blue, magenta, orange, random;
    private UiController uiController;
    private SimulationController simulationController;

    /**
     * The constructor
     * @param uiController - Represent the Driving class
     * @param simulationController - Represent the Driving class
     */
    public VehicleColor(UiController uiController,SimulationController simulationController) {
        super("Vehicle Color");
        this.uiController = uiController;
        this.simulationController = simulationController;
        blue = new JMenuItem("Blue");
        magenta = new JMenuItem("Magenta");
        orange = new JMenuItem("Orange");
        random = new JMenuItem("Random");
        this.add(blue);
        this.add(magenta);
        this.add(orange);
        this.add(random);
        blue.addActionListener(this);
        magenta.addActionListener(this);
        orange.addActionListener(this);
        random.addActionListener(this);

    }

    /**
     * Change the color of the vehicles by the selection of the user. If Blue - The all vehicles will be blue,
     * If orange - All will be orange, if magenta - All magenta, if random - every vehicle will be with different color.
     * @param e - The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int indexColor;
        Random rand = new Random();

        if (e.getSource().equals(blue)) {
            uiController.changeVehiclesColor(Color.BLUE);
        } else if (e.getSource().equals(orange)) {
            uiController.changeVehiclesColor(Color.ORANGE);
        } else if (e.getSource().equals(magenta)) {
            uiController.changeVehiclesColor(Color.MAGENTA);
        } else if (e.getSource().equals(random)) {
            for (Vehicle vehicle : ((Driving) uiController).getVehicles()) {
                vehicle.setColor(new Color(rand.nextInt(0xFFFFFF)));
                ((Driving)simulationController).refreshCanvas();


            }

        }
    }
}
