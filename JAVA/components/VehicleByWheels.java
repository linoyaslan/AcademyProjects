package components;

/**
 * Interface used to implement the design pattern: Abstract Factory
 */
public interface VehicleByWheels {
    Vehicle getVehicle(String property);// return new instance of vehicle by property
}
