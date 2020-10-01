/**
 *
 */
package components;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import components.Control.SimulationController;
import components.Control.UiController;
import ui.Entities.Drawable;
import ui.panels.Canvas;
import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Traffic control game
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public class Driving implements Utilities, Timer, UiController, SimulationController, Runnable {
    private Map map;
    private ArrayList<Vehicle> vehicles;
    private int drivingTime;
    private ArrayList<Timer> allTimedElements;
    private Canvas outputCanvas;
    private static boolean flagIsRunning;
    private static boolean stop;
    private JScrollPane sp = new JScrollPane();
    // private JFactory jFactory = new JFactory(this);
    //   private BigBrother bigBrother;


    /**
     * The constructor
     * @param outputCanvas - The canvas of the GUI system
     */
    public Driving(Canvas outputCanvas) {
        this.outputCanvas = outputCanvas;
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * @return the vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * @return the drivingTime
     */
    public int getDrivingTime() {
        return drivingTime;
    }

    /**
     * @param drivingTime the drivingTime to set
     */
    public void setDrivingTime(int drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * @return the allTimedElements
     */
    public ArrayList<Timer> getAllTimedElements() {
        return allTimedElements;
    }

    /**
     * @param allTimedElements the allTimedElements to set
     */
    public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
        this.allTimedElements = allTimedElements;
    }

    /**method runs the game for given quantity of turns
     * @param turns
     */
    public void drive(int turns) throws InterruptedException, IOException {
        System.out.println("\n================= START DRIVING=================");

        drivingTime = 0;
        for (int i = 0; i < turns; i++) {
            incrementDrivingTime();
        }
    }

    @Override
    public void incrementDrivingTime() throws InterruptedException, IOException {
        System.out.println("\n***************TURN " + drivingTime++ + "***************");
        for (Timer element : allTimedElements) {
            System.out.println(element);
            element.incrementDrivingTime();
            System.out.println();
        }

    }

    @Override
    public String toString() {
        return "Driving [map=" + map + ", vehicles=" + vehicles + ", drivingTime=" + drivingTime + ", allTimedElements="
                + allTimedElements + "]";
    }

    /**
     * The create function - Create the map of the program, the vehicles and add the drawable objects
     * to the drawable list in the canvas.
     * @param numOfJunctions - The number of junctions
     * @param numOfVehicle - The number of vehicles
     */
    @Override
    public void create(int numOfJunctions, int numOfVehicle) {
        Vehicle.setObjectsCount(1);
        Junction.setObjectsCount(1);
        outputCanvas.clear();
        vehicles = new ArrayList<Vehicle>();
        allTimedElements = new ArrayList<Timer>();
        //  bigBrother = BigBrother.getBigBrother();
        drivingTime = 0;
        map = new Map(numOfJunctions, this);

        System.out.println("\n================= CREATING VEHICLES =================");

        while (vehicles.size() < numOfVehicle) {
            Road temp = map.getRoads().get(getRandomInt(0, map.getRoads().size()));//random road from the map
            if (temp.getEnabled()) {
                int tempNumOfWheels = whichNumberOfWheels();
                vehicles.add((new VehicleFactory(this)).getFactory(tempNumOfWheels).getVehicle(whichSuitableTypeOfVeh(tempNumOfWheels)));
                // vehicles.get(vehicles.size()-1).setLastRoad(temp);
                // vehicles.get(vehicles.size()-1).setCurrentRoute(new Route(temp,vehicles.get(vehicles.size()-1)));
            }

        }

        allTimedElements.addAll(vehicles);

        for (TrafficLights light : map.getLights()) {
            if (light.getTrafficLightsOn()) {
                allTimedElements.add(light);

            }
        }

        for (Road road : getMap().getRoads()) {
            outputCanvas.addDrawable(road);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (junction instanceof LightedJunction)
                outputCanvas.addDrawable(junction);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (!(junction instanceof LightedJunction)) {
                outputCanvas.addDrawable(junction);
            }
        }

        for (Vehicle vehicle : getVehicles()) {
            outputCanvas.addDrawable(vehicle);
        }
        refreshCanvas();
    }

    /**
     *A function that returns a random number of wheels
     * @return num of wheels
     */
    public int whichNumberOfWheels() {
        Random rand = new Random();
        int theRandNum = rand.nextInt(3);
        int numberOfWheels = 0;
        if (theRandNum == 0) {
            numberOfWheels = 2;
        } else if (theRandNum == 1) {
            numberOfWheels = 4;

        } else if (theRandNum == 2) {
            numberOfWheels = 10;
        }
        return numberOfWheels;
    }

    /**
     *A function that accepts several wheels of a vehicle and, accordingly, randomly returns its property
     * @param numberOfWheels - number of wheels of the Vehicle
     * @return string of property
     */
    public String whichSuitableTypeOfVeh(int numberOfWheels) {
        String[] twoWheels = {"fast", "slow"};
        String[] fourWheels = {"private", "work", "public"};
        String[] tenWheels = {"public", "work"};
        Random rand = new Random();
        String theReturnString = "";
        int randString;
        if (numberOfWheels == 2) {
            randString = rand.nextInt(twoWheels.length);
            theReturnString = twoWheels[randString];
        } else if (numberOfWheels == 4) {
            randString = rand.nextInt(fourWheels.length);
            theReturnString = fourWheels[randString];
        } else if (numberOfWheels == 10) {
            randString = rand.nextInt(tenWheels.length);
            theReturnString = tenWheels[randString];
        }
        return theReturnString;
    }


    /**
     * Here the all threads in the program are start
     */
    @Override
    public void start() {
        flagIsRunning = true;
        allTimedElements.stream().filter(e -> e instanceof Runnable).forEach(e -> {
            new Thread((Runnable) e).start();
        });
        new Thread(this).start();
    }

    /**
     * The boolean attribute "stop" is true now, so in the run method the all thread will do a wait
     */
    @Override
    public void stop() {
        stop = true;
    }

    /**
     * The all threads in the program will do notify ("wake up")
     */
    @Override
    public synchronized void resume() {
        stop = false;
        for (Timer elem : allTimedElements) {
            elem.resume();
        }
        notify();
    }

    /**
     * The all information of the vehicles will represent in a table
     * @param flag - an helping boolean variable
     */
    public void Info(boolean flag) {
        if (flag) {
            Vector<String> columnNames = new Vector<String>();
            Vector<Vector<String>> data = new Vector<Vector<String>>();

            if (this == null)
                return;
            /* The all information about the vehicles */
            for (Vehicle v : vehicles) {
                Vector<String> row = new Vector<String>();
                row.add(String.valueOf(v.getId()));
                row.add(v.getVehicleType().toString());
                if (v.getCurrentRoutePart() instanceof Junction) {
                    Junction j = (Junction) v.getCurrentRoutePart();
                    row.add("Junction " + j.getJunctionName());
                }
                if (v.getCurrentRoutePart() instanceof Road) {
                    row.add("Road " + v.getLastRoad().getStartJunction().getJunctionName() + "-" + v.getLastRoad().getEndJunction().getJunctionName());
                }
                row.add(String.valueOf(v.getTimeOnCurrentPart()));
                row.add(String.valueOf((int) (v.getVehicleSpeed() * 10)));
                data.add(row);
            }
            /* The columns */
            columnNames.add("Vehicle #");
            columnNames.add("Type");
            columnNames.add("Location");
            columnNames.add("Time on loc");
            columnNames.add("Speed");
            JTable table = new JTable(new DefaultTableModel(data, columnNames));
            sp = new JScrollPane(table);
            sp.setBounds(0, 0, 400, 100);
            outputCanvas.add(sp);
        } else {
            sp.setVisible(false);
        }
    }

    /**
     * Getter of JScrollPane
     * @return - return the JScrollPane
     */
    public JScrollPane getSp() {
        return sp;
    }

    /**
     * Setter of JScrollPane
     * @param sp - A JScrollPane that sent to this method
     */
    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    /**
     * Change the background canvas color with the color that sent to this method
     * @param newColor - A color that sent
     */
    @Override
    public void changeBackground(Color newColor) {
        outputCanvas.setBackground(newColor);
    }

    /**
     * Change the vehicles color
     * @param newColor - The color that will be for the vehicles
     */
    @Override
    public void changeVehiclesColor(Color newColor) {
        for (Drawable draw : outputCanvas.getDrawables()) {
            if (draw instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) draw;
                vehicle.setColor(newColor);
            }
        }
        refreshCanvas();
    }

    /**
     * Do a repaint to the all drawable objects in the canvas
     */
    public void refreshCanvas() {
        outputCanvas.repaint();

    }

    /**
     * The run method to run the Driving thread
     */
    @Override
    public void run() {
        while (flagIsRunning) {
            try {
                Thread.sleep(100);
                while (stop) {
                    synchronized (this) {
                        wait();
                    }
                }
                refreshCanvas();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Setter - set the boolean variable that sent to the flagIsRunning attribute
     * @param flagIsRunning - A help boolean variable - If true the thread is running, otherwise - not running
     */
    public void setFlagIsRunning(boolean flagIsRunning) {
        this.flagIsRunning = flagIsRunning;
    }

    /**
     * Getter - get the boolean variable - flagIsRunning attribute
     * @return - A help boolean variable - If true the thread is running, otherwise - not running
     */
    public boolean getFlagIsRuning() {
        return flagIsRunning;
    }

    /**
     * Getter - get the boolean variable - stop attribute
     * @return - A help boolean variable - If true the thread is do wait, otherwise - notify
     */
    public boolean getStop() {
        return stop;
    }

    /**
     * Setter - set the boolean variable that sent - stop attribute
     * @return - A help boolean variable - If true the thread is do wait, otherwise - notify
     */
    public void setStop(boolean value) {
        this.stop = value;
    }

    /**
     *A function that creates and draws a map of a country, this function uses a design pattern: Abstract Factory , Builder,Prototype
     * @param numOfVehicles - number of vehicle
     */
    @Override
    public void createCountry(int numOfVehicles) {
        Vehicle cloneVehicle;
        ArrayList<String> propertyOfVehList = new ArrayList<String>();
        MapBuilder countryMap = new CountryMap(this);
        RoadEngineer roadEngineer = new RoadEngineer(countryMap);
        roadEngineer.constructMap();
        Map map = roadEngineer.getMap();
        Vehicle vehicletemp;
        setMap(map);

        Vehicle.setObjectsCount(1);
        Junction.setObjectsCount(1);
        outputCanvas.clear();
        vehicles = new ArrayList<Vehicle>();
        allTimedElements = new ArrayList<Timer>();
        drivingTime = 0;

        System.out.println("\n================= CREATING VEHICLES =================");

        while (vehicles.size() < numOfVehicles) {
            Road temp = map.getRoads().get(getRandomInt(0, map.getRoads().size()));//random road from the map
            if (temp.getEnabled()) {
                int tempNumOfWheels = randNumberOfWheels("country");
                String propertyOfVeh = whichSuitableTypeOfVeh(tempNumOfWheels);
                if (!propertyOfVehList.contains(propertyOfVeh)) {
                    vehicletemp = (new VehicleFactory(this)).getFactory(tempNumOfWheels).getVehicle(propertyOfVeh);
                    propertyOfVehList.add(propertyOfVeh);
                    vehicles.add(vehicletemp);
                } else {
                    for (int i = 0; i < vehicles.size(); i++) {
                        if (vehicles.get(i).getProperty() == propertyOfVeh && tempNumOfWheels == vehicles.get(i).getNumOfWheels()) {
                            Vehicle v =(Vehicle) vehicles.get(i).clone();
                            v.setCurrentRoute(new Route(map.getRoads().get(getRandomInt(0,getMap().getRoads().size())),v));
                            vehicles.add(v);
                            break;
                        }
                    }
                }
                // vehicles.get(vehicles.size()-1).setLastRoad(temp);
                // vehicles.get(vehicles.size()-1).setCurrentRoute(new Route(temp,vehicles.get(vehicles.size()-1)));
            }

        }

        allTimedElements.addAll(vehicles);

        for (TrafficLights light : map.getLights()) {
            if (light.getTrafficLightsOn()) {
                allTimedElements.add(light);

            }
        }

        for (Road road : getMap().getRoads()) {
            outputCanvas.addDrawable(road);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (junction instanceof LightedJunction)
                outputCanvas.addDrawable(junction);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (!(junction instanceof LightedJunction)) {
                outputCanvas.addDrawable(junction);
            }
        }

        for (Vehicle vehicle : getVehicles()) {
            outputCanvas.addDrawable(vehicle);
        }
        refreshCanvas();
    }

    /**
     *A function that creates and draws a map of a city, this function uses a design pattern: Abstract Factory , Builder,Prototype
     * @param numOfVehicles - number of vehicle
     */
    @Override
    public void createCity(int numOfVehicles) {
        ArrayList<String> propertyOfVehList = new ArrayList<String>();
        MapBuilder cityMap = new CityMap(this);
        RoadEngineer roadEngineer = new RoadEngineer(cityMap);
        roadEngineer.constructMap();
        Map map = roadEngineer.getMap();
        setMap(map);

        Vehicle.setObjectsCount(1);
        Junction.setObjectsCount(1);
        outputCanvas.clear();
        vehicles = new ArrayList<Vehicle>();
        allTimedElements = new ArrayList<Timer>();
        drivingTime = 0;

        System.out.println("\n================= CREATING VEHICLES =================");

        while (vehicles.size() < numOfVehicles) {
            Random rand = new Random();
            boolean randSpeed;
            Road temp = map.getRoads().get(getRandomInt(0, map.getRoads().size()));//random road from the map
            Vehicle vehicle;
            if (temp.getEnabled()) {
                int tempNumOfWheels = randNumberOfWheels("city");
                String propertyOfVeh = whichSuitableTypeOfVeh(tempNumOfWheels);
                if (!propertyOfVehList.contains(propertyOfVeh)) {
                    vehicle = (new VehicleFactory(this)).getFactory(tempNumOfWheels).getVehicle(propertyOfVeh);
                    randSpeed = rand.nextBoolean();
                    if (randSpeed) {
                        vehicle.setVehicleSpeed(vehicle.getVehicleType().getAverageSpeed() * 1.3);
                    } else {
                        vehicle.setVehicleSpeed(vehicle.getVehicleType().getAverageSpeed());
                    }
                    propertyOfVehList.add(propertyOfVeh);
                    vehicles.add(vehicle);
                } else {
                    for (int i = 0; i < vehicles.size(); i++) {
                        if (vehicles.get(i).getProperty() == propertyOfVeh) {
                            Vehicle v =(Vehicle) vehicles.get(i).clone();
                            v.setCurrentRoute(new Route(map.getRoads().get(getRandomInt(0,getMap().getRoads().size())),v));
                            vehicles.add(v);
                            break;
                        }
                    }
                }


                // vehicles.get(vehicles.size()-1).setLastRoad(temp);
                // vehicles.get(vehicles.size()-1).setCurrentRoute(new Route(temp,vehicles.get(vehicles.size()-1)));

            }

        }

        allTimedElements.addAll(vehicles);

        for (TrafficLights light : map.getLights()) {
            if (light.getTrafficLightsOn()) {
                allTimedElements.add(light);

            }
        }

        for (Road road : getMap().getRoads()) {
            outputCanvas.addDrawable(road);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (junction instanceof LightedJunction)
                outputCanvas.addDrawable(junction);
        }

        for (Junction junction : getMap().getJunctions()) {
            if (!(junction instanceof LightedJunction)) {
                outputCanvas.addDrawable(junction);
            }
        }

        for (Vehicle vehicle : getVehicles()) {
            outputCanvas.addDrawable(vehicle);
        }
        refreshCanvas();
    }

    /**
     * A function that accepts the map type and randomly returns the number of wheels by the type of map we received
     * @param typeOfMap - type of map : city or country
     * @return theReturnnumOfWheels  - number of wheelse
     */
    public int randNumberOfWheels(String typeOfMap) {
        Random rand = new Random();
        int randNumOfWheels = rand.nextInt(1);
        int theReturnnumOfWheels = 0;
        if (typeOfMap == "city") {
            if (randNumOfWheels == 0) {
                theReturnnumOfWheels = 2;
            } else {
                theReturnnumOfWheels = 4;
            }
        } else if (typeOfMap == "country") {
            if (randNumOfWheels == 0) {
                theReturnnumOfWheels = 4;
            } else {
                theReturnnumOfWheels = 10;
            }
        }
        return theReturnnumOfWheels;
    }

    /**
     * Getter - get the canvas of the GUI system
     * @return - the canvas
     */
    public Canvas getOutputCanvas() {
        return outputCanvas;
    }

    /**
     * Setter - Set the canvas that sent to the outputCanvas attribute
     * @param outputCanvas - The canvas of the GUI system
     */
    public void setOutputCanvas(Canvas outputCanvas) {
        this.outputCanvas = outputCanvas;
    }
}
