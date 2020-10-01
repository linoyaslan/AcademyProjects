/**
 *
 */
package components;


import components.Control.SimulationController;
import ui.Entities.ColoredEntity;
import ui.Entities.Drawable;
import utilities.Point;
import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Random;

/**
 * Represent a Vehicle
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public class Vehicle extends Observable implements Utilities, Timer, Drawable, ColoredEntity, Runnable,Cloneable{
    private final int D = 10;
    private final int H = 4;
    private int id;
    private VehicleType vehicleType;
    private Driver driver;
    private Route currentRoute;
    private RouteParts currentRoutePart;
    private int timeFromRouteStart;
    private static int objectsCount = 1;
    private int timeOnCurrentPart;
    private Road lastRoad;
    private String status;
    private Color color = Color.BLUE;
    private SimulationController simulationController;
    private BigBrother bigBrother;
    private double vehicleSpeed;
    private String property;
    private int numOfWheels;
    /**Random Constructor
     * @param currentLocation
     */
    public Vehicle(Road currentLocation, SimulationController simulationController) {
        this.simulationController = simulationController;
        id = objectsCount++;
        vehicleType = currentLocation.getVehicleTypes()[getRandomInt(0, currentLocation.getVehicleTypes().length - 1)];
        bigBrother = BigBrother.getBigBrother();
        System.out.println();
        successMessage(this.toString());
        currentRoute = new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
        lastRoad = currentLocation;
        status = null;
        driver = new Driver(id,bigBrother.getMoked());
        addObserver(bigBrother);
        property = vehicleType.getPropertyType(vehicleType);
        numOfWheels = vehicleType.getNumOfWheelseByType(vehicleType);
        randSpeedOfVeh();

    }

    public Vehicle(VehicleType type , SimulationController simulationController) {
        this.simulationController = simulationController;
        id = objectsCount++;
        vehicleType = type;
        bigBrother = BigBrother.getBigBrother();
        System.out.println();
        successMessage(this.toString());
        lastRoad = randRoadSuitableToVeh(type);
        currentRoute = new Route(lastRoad, this); //creates a new route for the vehicle and checks it in
        status = null;
        driver = new Driver(id,bigBrother.getMoked());
        addObserver(bigBrother);
        property = vehicleType.getPropertyType(vehicleType);
        numOfWheels = vehicleType.getNumOfWheelseByType(vehicleType);
        randSpeedOfVeh();
    }



    public Vehicle(Vehicle other){
        this.simulationController = other.simulationController;
        this.id = objectsCount++;
        this.vehicleType = other.vehicleType;
        this.bigBrother = other.bigBrother;
        System.out.println();
        successMessage(this.toString());
        currentRoute = new Route(other.currentRoute.getFirstPart(), this);
        this.lastRoad = (Road)other.currentRoute.getFirstPart();
        status = null;
        this.driver = new Driver(id,bigBrother.getMoked());
        addObserver(bigBrother);
        property = vehicleType.getPropertyType(vehicleType);
        numOfWheels = vehicleType.getNumOfWheelseByType(vehicleType);
        randSpeedOfVeh();

    }
    public void randSpeedOfVeh(){
        Random rand = new Random();
        boolean randSpeed = rand.nextBoolean();
        if (randSpeed){
            vehicleSpeed = vehicleType.getAverageSpeed();
        }
        else {
            vehicleSpeed = (vehicleType.getAverageSpeed())*1.5;
        }
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    public void setNumOfWheels(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }

    public String getProperty() {
        return property;
    }

    public Road randRoadSuitableToVeh(VehicleType vehicleType){
       /* for(Road road : ((Driving)simulationController).getMap().getRoads()){
            if(Arrays.asList(road.getVehicleTypes()).contains(vehicleType)){
                return road;
            }
        }*/
        return ((Driving)simulationController).getMap().getRoads().get(getRandomInt(0,((Driving)simulationController).getMap().getRoads().size()-1));

    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the vehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }


    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    /**
     * @return the currentRoute
     */
    public Route getCurrentRoute() {
        return currentRoute;
    }


    /**
     * @param currentRoute the currentRoute to set
     */
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }


    /**
     * @return the currentRoutePart
     */
    public RouteParts getCurrentRoutePart() {
        return currentRoutePart;
    }

    public Driver getDriver() {
        return driver;
    }

    /**
     * @param currentRoutePart the currentRoutePart to set
     */
    public void setCurrentRoutePart(RouteParts currentRoutePart) {
        this.currentRoutePart = currentRoutePart;
    }


    /**
     * @return the timeFromRouteStart
     */
    public int getTimeFromRouteStart() {
        return timeFromRouteStart;
    }

    public BigBrother getBigBrother() {
        return bigBrother;
    }

    /**
     * @param timeFromRouteStart the timeFromRouteStart to set
     */
    public void setTimeFromRouteStart(int timeFromRouteStart) {
        this.timeFromRouteStart = timeFromRouteStart;
    }


    /**
     * @return the timeOnCurrentPart
     */
    public int getTimeOnCurrentPart() {
        return timeOnCurrentPart;
    }


    /**
     * @param timeOnCurrentPart the timeOnCurrentPart to set
     */
    public void setTimeOnCurrentPart(int timeOnCurrentPart) {
        this.timeOnCurrentPart = timeOnCurrentPart;
    }


    /**
     * @return the lastRoad
     */
    public Road getLastRoad() {
        return lastRoad;
    }


    /**
     * @param lastRoad the lastRoad to set
     */
    public void setLastRoad(Road lastRoad) {
        this.lastRoad = lastRoad;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return the objectsCount
     */
    public static int getObjectsCount() {
        return objectsCount;
    }


    @Override
    public void incrementDrivingTime() throws InterruptedException, IOException {
        timeFromRouteStart++;
        timeOnCurrentPart++;
        move();
    }

    /**controls the vehicle moving from one route part to the next one
     *
     */
    public synchronized void move() throws InterruptedException, IOException {
        if (currentRoutePart.canLeave(this)) {
            currentRoutePart.checkOut(this);
            currentRoute.findNextPart(this).checkIn(this);

            if (currentRoutePart instanceof Junction) {
                setChanged();
                notifyObservers();
            }
        } else {
            currentRoutePart.stayOnCurrentPart(this);
        }

    }


    @Override
    public String toString() {
        return new String("Vehicle " + id + ": " + getVehicleType().name() + ", average speed: " + getVehicleType().getAverageSpeed());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Vehicle other = (Vehicle) obj;
        if (this.currentRoute != other.currentRoute ||
                this.currentRoutePart != other.currentRoutePart ||
                this.id != other.id ||
                this.lastRoad != other.lastRoad ||
                this.status != other.status ||
                this.timeFromRouteStart != other.timeFromRouteStart ||
                this.timeOnCurrentPart != other.timeOnCurrentPart ||
                this.vehicleType != other.vehicleType)
            return false;
        return true;
    }

    /**
     * @param objectsCount the objectsCount to set
     */
    public static void setObjectsCount(int objectsCount) {
        Vehicle.objectsCount = objectsCount;
    }

    /**
     * Activate the drawRotatedVehicle method - draw a vehicle -
     * a rectangle 10X8 with 4 wheels. if the vehicle waiting in a junction the draw is a square 8X8 with wheels
     * @param g
     */
    public void draw(Graphics g) {
        drawRotetedVehicle(g, (int) vehicleXLocation(currentRoutePart), (int) vehicleYLocation(currentRoutePart), (int) lastRoad.endJunction.getX(), (int) lastRoad.endJunction.getY(), D, H);
    }

    /**
     * draw a vehicle -
     * a rectangle 10X8 with 4 wheels. if the vehicle waiting in a junction the draw is a square 8X8 with wheels
     * @param g
     * @param x1 - The x location
     * @param y1 - The y location
     * @param x2 - the x of the endJunction
     * @param y2 - the y of the endJunction
     * @param d - Length of the vehicle
     * @param h - Half the width of the vehicle
     */
    private void drawRotetedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1, delta = 10;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = delta, xn = xm, ym = h, yn = -h, x;
        double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
        double sin = dy / D, cos = dx / D;
        if (currentRoutePart instanceof Road) {
            x = xm * cos - ym * sin + x1;
            xx = xm1 * cos - ym1 * sin + x1;
            ym = xm * sin + ym * cos + y1;
            ym1 = xm1 * sin + ym1 * cos + y1;
            xm = x;
            xm1 = xx;
            x = xn * cos - yn * sin + x1;
            xx = xn1 * cos - yn1 * sin + x1;
            yn = xn * sin + yn * cos + y1;
            yn1 = xn1 * sin + yn1 * cos + y1;
            xn = x;
            xn1 = xx;
            int[] xpoints = {(int) xm1, (int) xn1, (int) xn, (int) xm};
            int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
            g.setColor(getColor());
            g.fillPolygon(xpoints, ypoints, 4);
            g.setColor(Color.BLACK);
            g.fillOval((int) xm1 - 2, (int) ym1 - 2, 4, 4);
            g.fillOval((int) xn1 - 2, (int) yn1 - 2, 4, 4);
            g.fillOval((int) xm - 2, (int) ym - 2, 4, 4);
            g.fillOval((int) xn - 2, (int) yn - 2, 4, 4);
        } else {
            g.setColor(this.getColor());
            int[] xpoints = {(int) x2 - 4, (int) x2 + 4, (int) x2 + 4, (int) x2 - 4};
            int[] ypoints = {(int) y2 + 4, (int) y2 + 4, (int) y2 - 4, (int) y2 - 4};
            g.fillPolygon(xpoints, ypoints, 4);
            g.setColor(Color.BLACK);
            g.fillOval((int) x2 - 5 - 2, (int) y2, 4, 4);
            g.fillOval((int) x2 + 5 - 2, (int) y2, 4, 4);
            g.fillOval((int) x2 - 5 - 2, (int) y2 - 2 - 2, 4, 4);
            g.fillOval((int) x2 + 5 - 2, (int) y2 - 2 - 2, 4, 4);
        }

    }

    /**
     * The x location of the vehicle
     * @param currentRoutePart - The current route part of the vehicle
     * @return - return the x location of the vehicle
     */
    public double vehicleXLocation(RouteParts currentRoutePart) {
        double vehicleLength;
        double restLength;
        double x;
        if (currentRoutePart instanceof Junction) {
            return ((Junction) currentRoutePart).getX();
        } else {
            Road road = ((Road) currentRoutePart);
            vehicleLength = timeOnCurrentPart * vehicleType.getAverageSpeed();
            restLength = road.length - vehicleLength;
            x = ((road.startJunction.getX() * restLength) + (road.endJunction.getX() * vehicleLength)) / road.length;
            return x;
        }
    }

    /**
     * The y location of the vehicle
     * @param currentRoutePart - The current route part of the vehicle
     * @return - return the y location of the vehicle
     */
    public double vehicleYLocation(RouteParts currentRoutePart) {
        double vehicleLength;
        double restLength;
        double y;
        if (currentRoutePart instanceof Junction) {
            return ((Junction) currentRoutePart).getY();
        } else {
            Road road = ((Road) currentRoutePart);
            vehicleLength = timeOnCurrentPart * vehicleType.getAverageSpeed();
            restLength = road.length - vehicleLength;
            y = ((road.startJunction.getY() * restLength) + (road.endJunction.getY() * vehicleLength)) / road.length;
            return y;
        }
    }

    /**
     * get the color of the vehicle
     * @return - the color of the vehicle
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Set the color that sent to the color of the vehicle
     * @param color - the color of the vehicle
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * do notify to a vehicle thread
     */
    public synchronized void resume() {
        this.notify();
    }

    /**
     * The run method of vehicle - running a thread of a vehicle
     */
    @Override
    public void run() {
        while (simulationController.getFlagIsRuning()) {
            try {
                Thread.sleep(100);
                while (simulationController.getStop()) {
                    if (!(simulationController.getFlagIsRuning())) {
                        return;
                    }
                    synchronized (this) {
                        wait();
                    }
                }
                incrementDrivingTime();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object clone(){
//        Object clone = null;
////        try{
////            clone = super.clone();
////        } catch (CloneNotSupportedException e) {
////            e.printStackTrace();
////        }
////        return clone;
        return new Vehicle(this);
    }

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(double vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }
}
