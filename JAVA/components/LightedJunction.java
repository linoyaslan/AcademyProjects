/**
 *
 */
package components;


import components.Control.SimulationController;

import java.awt.*;

/** Represents a Junction with traffic lights
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public class LightedJunction extends Junction {

    TrafficLights lights;
    private SimulationController simulationController;


    /**Constructor
     * @param name junction name
     * @param x coordinate
     * @param y coordinate
     * @param sequential represents the type of traffic lights: random or sequential
     * @param lightsOn represents the state of traffic lights: turned on/off
     */
    public LightedJunction(String name, double x, double y, boolean sequential, boolean lightsOn, SimulationController simulationController) {
        super(name, x, y);
        if (sequential) {
            lights = new SequentialTrafficLights(this.getEnteringRoads(), simulationController);
        } else {
            lights = new RandomTrafficLights(this.getEnteringRoads(), simulationController);
        }
        if (lightsOn) {
            lights.setTrafficLightsOn(true);
        }
        successMessage(String.format("Junction %s  (%.2f , %.2f), Lighted", getJunctionName(), getX(), getY()));
    }

    /**Random Constructor
     * creates random traffic lights for the junction.
     */
    public LightedJunction(SimulationController simulationController) {
        super();
        this.simulationController = simulationController;
        if (this.getRandomBoolean()) {
            lights = new SequentialTrafficLights(this.getEnteringRoads(), simulationController);
        } else {
            lights = new RandomTrafficLights(this.getEnteringRoads(), simulationController);
        }

        successMessage(String.format("Junction %s (%.2f , %.2f), Lighted", getJunctionName(), getX(), getY()));
    }

    /**
     * @return the lights
     */
    public TrafficLights getLights() {
        return lights;
    }

    /**
     * @param lights the lights to set
     */
    public void setLights(TrafficLights lights) {
        this.lights = lights;
    }

    @Override
    public double calcEstimatedTime(Object obj) {
        if (!lights.getTrafficLightsOn())
            return super.calcEstimatedTime(obj);
        else return (getEnteringRoads().size() - 1) * lights.getDelay() + 1;
    }

    @Override
    public String toString() {

        return new String(super.toString() + " (Lighted)");
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        if (!lights.equals(((LightedJunction) other).getLights())) return false;
        return true;
    }

    @Override
    public boolean canLeave(Vehicle vehicle) {
        if (!lights.getTrafficLightsOn()) {
            return super.canLeave(vehicle);
        }
        if (!checkAvailability(vehicle)) {
            return false;
        }
        if (!vehicle.getLastRoad().getGreenLight()) {
            vehicle.setStatus(new String(" for green light"));
            return false;
        }
        return true;
    }

    /**
     * The color of the lighted junction - If the trafficLight is ON the color is RED, otherwise is GREEN
     * @return - The color of the lightedJunction
     */
    @Override
    public Color getColor() {
        if (lights.getTrafficLightsOn()) {
            return Color.RED;
        }
        return Color.GREEN;
    }

    /**
     * activate the ftunction "drawArrow" -
     * Draw a green arrow that represent a LightedJunction with trafficLight ON and that the enteringRoad is with greenLight
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        if (this.getLights().getTrafficLightsOn()) {
            drawArrow(g, (int) this.getEnteringRoads().get(this.lights.getGreenLightIndex()).getStartJunction().getX(), (int) this.getEnteringRoads().get(this.lights.getGreenLightIndex()).getStartJunction().getY(), (int) ((int) this.getEnteringRoads().get(this.lights.getGreenLightIndex()).getEndJunction().getX()), (int) ((int) this.getEnteringRoads().get(this.lights.getGreenLightIndex()).getEndJunction().getY()), 27, 3);
        }
        super.draw(g);
    }

    /**
     * The drawing of the arrow
     * @param g
     * @param x1 - The x of the point of the start junction
     * @param y1 - The y of the point of the start junction
     * @param x2 - The x of the point of the end junction
     * @param y2 - The y of the point of the end junction
     * @param d - height
     * @param h - weight
     */
    public void drawArrow(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
        g.setColor(Color.GREEN);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
