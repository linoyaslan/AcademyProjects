package ui.panels;

import components.Control.SimulationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The createRoadSystem dialog - Open after the user select the createRoadSystem button
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class CreateRoadsSystemFrame extends JDialog {
    JSlider junctionSlider;
    JSlider vehicleSlider;
    private SimulationController simulationController;

    /**
     * An "Anonymous" ActionListener class - If the user is select the OK button
     * Here The "create" method of Driving class is activate
     */
    private ActionListener okActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int numOfJunctions = junctionSlider.getValue();
            int numOfVehicles = vehicleSlider.getValue();
            simulationController.create(numOfJunctions, numOfVehicles);
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
     * @param owner  - JFrame
     * @param simulationController - The Driving class
     * @throws HeadlessException - An exception
     */
    public CreateRoadsSystemFrame(JFrame owner, SimulationController simulationController) throws HeadlessException {
        super(owner, "Create road system");
        this.setPreferredSize(new Dimension(600, 300));
        this.pack();
        this.simulationController = simulationController;
        JPanel mainPanel = new JPanel();

        JPanel numofJunction = new JPanel();
        JLabel labeljunction = new JLabel("Number of junctions");
        labeljunction.setHorizontalAlignment(JLabel.CENTER);
        numofJunction.add(labeljunction);
        numofJunction.setLayout(new GridLayout(2,1));

        /* The junctions slider of this dialog */
        junctionSlider = new JSlider(JSlider.HORIZONTAL, 3, 20, 11);
        junctionSlider.setPreferredSize(new Dimension(580,50));
        junctionSlider.setMajorTickSpacing(1);
        junctionSlider.setPaintTicks(true);
        junctionSlider.setPaintLabels(true);
        numofJunction.add(junctionSlider);

        JPanel numofVehicles = new JPanel();
        JLabel labelvehicles = new JLabel("Number of vehicles");
        labelvehicles.setHorizontalAlignment(JLabel.CENTER);
        numofVehicles.add(labelvehicles);
        numofVehicles.setLayout(new GridLayout(2,1));

        /* The vehicle slider of this dialog */
        vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        vehicleSlider.setPreferredSize(new Dimension(580,50));
        vehicleSlider.setMajorTickSpacing(5);
        vehicleSlider.setMinorTickSpacing(1);
        vehicleSlider.setPaintTicks(true);
        vehicleSlider.setPaintLabels(true);
        numofVehicles.add(vehicleSlider);

        /* Add the "OK" and "Cancel" buttons and design them */
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2));
        JButton ok = new JButton("OK");
        ok.setBackground(Color.GRAY);
        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.GRAY);
        buttons.setPreferredSize(new Dimension(600,30));
        buttons.add(ok,BorderLayout.SOUTH);
        buttons.add(cancel,BorderLayout.SOUTH);

        mainPanel.add(numofJunction, BorderLayout.NORTH);
        mainPanel.add(numofVehicles, BorderLayout.CENTER);
        mainPanel.add(buttons, BorderLayout.SOUTH);

        this.add(mainPanel);

        ok.addActionListener(okActionListener);
        cancel.addActionListener(cancelActionListener);
    }

    /**
     * Close method - close this dialog
     */
    private void close() {
        this.setVisible(false);
    }


}
