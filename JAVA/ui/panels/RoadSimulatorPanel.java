package ui.panels;

import components.*;
import ui.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * The panel of the all parts of the GUI system
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class RoadSimulatorPanel extends JPanel {

    private Driving driving;
    private Canvas canvas;

    /**
     * The constructor
     */
    public RoadSimulatorPanel() {
        super();
        this.setLayout(new BorderLayout());

        canvas = new Canvas(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        driving = new Driving(canvas);
        ButtonsBar buttonsBar = new ButtonsBar(Constants.CANVAS_WIDTH, Constants.BUTTON_BAR_HEIGHT,driving);
        MainMenu mainMenu = new MainMenu(Constants.CANVAS_WIDTH, 25,driving,driving);

        this.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT + Constants.BUTTON_BAR_HEIGHT));
        this.add(canvas, BorderLayout.CENTER);
        this.add(buttonsBar, BorderLayout.SOUTH);
        this.add(mainMenu, BorderLayout.NORTH);

    }

}
