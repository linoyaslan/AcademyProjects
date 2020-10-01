package ui.panels;

import components.Control.SimulationController;
import components.Control.UiController;

import javax.swing.*;
import java.awt.*;

/**
 * The main menu of the GUI system - includes the File, Background, Vehicle Color and help sub menus.
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class MainMenu extends JMenuBar {
    private final int width;
    private final int height;
    private Color color;
    private UiController uiController;
    private SimulationController simulationController;
    private JMenu file, background, vehiclecolor, help,cloneACar,reports,buildAMap;

    /**
     * The constructor
     * @param width - The width of the main menu
     * @param height - The height of the main menu
     * @param uiController - Represent the Driving class
     * @param simulationController - Represent the Driving class
     */
    MainMenu(int width, int height, UiController uiController,SimulationController simulationController) {
        this.width = width;
        this.height = height;
        this.uiController = uiController;
        this.simulationController = simulationController;
        color = Color.WHITE;
        file = new File(simulationController);
        background = new Background(uiController);
        vehiclecolor = new VehicleColor(uiController,simulationController);
        cloneACar = new CloneACar(simulationController);
        buildAMap = new BuildAMap(uiController,simulationController);
        reports = new Reports(simulationController);
        help = new Help();
        this.add(file);
        this.add(background);
        this.add(vehiclecolor);
        this.add(buildAMap);
        this.add(cloneACar);
        this.add(reports);
        this.add(help);
        this.setPreferredSize(new Dimension(width, height));
    }

}
