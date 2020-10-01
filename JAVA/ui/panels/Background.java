package ui.panels;

import components.Control.UiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class represent the Background menu - change the background of the canvas
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Background extends JMenu implements ActionListener {
    private JMenuItem blue; /* A blue background */
    private JMenuItem none; /* A white background */
    private UiController uiController;

    /**
     * The constructor
     * @param uiController - Represent the Driving class
     */
    public Background(UiController uiController){
        super("Background"); /* The name of the menu */
        this.uiController = uiController;
        blue = new JMenuItem("Blue"); /* A sub menu of background */
        none = new JMenuItem("None"); /* Another sub menu of the background */

        /* add to the sub-menus to the Background menu */
        this.add(blue);
        this.add(none);

        /* add the ActionLisener of the sub-menus to the Background menu */
        blue.addActionListener(this);
        none.addActionListener(this);
    }

    /**
     * If the user select blue sub menu - The canvas background will be blue, Otherwise will be white
     * @param e - The selection of the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(blue)){
            uiController.changeBackground(Color.blue);
        }
        else if(e.getSource().equals(none)){
            uiController.changeBackground(Color.WHITE);
        }

    }
}
