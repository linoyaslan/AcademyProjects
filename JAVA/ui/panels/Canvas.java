package ui.panels;

import components.Driving;
import components.Junction;
import components.Road;
import ui.Entities.ColoredEntity;
import ui.Entities.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The canvas of the system - Here the drawing is "happened"
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 */
public class Canvas extends JPanel {

    private final int width;
    private final int height;
    private final List<Drawable> drawables = new ArrayList<>(); /* A list of the all objects that will draw in the canvas */

    /**
     * The constructor
     * @param width - The width of the canvas
     * @param height - The height of the canvas
     */
    public Canvas(int width, int height ) {
        this.width = width;
        this.height = height;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Realized the paintComponent method - Activate the draw function of every Drawable object in the list
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }

    /**
     * Add a drawable object to the list
     * @param drawable - An object that the class of it implements the Drawable interface
     */
    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    /**
     * get the list of the all drawables objects
     * @return - The list of the drawables objects
     */
    public List<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Clear the canvas
     */
    public void clear() {
        drawables.clear();
    }

}
