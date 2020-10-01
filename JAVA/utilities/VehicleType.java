/**
 * 
 */
package utilities;


import java.util.Random;

/**
 * @author Tzlil Levi (ID: 206796088) and Linoy Aslan (ID: 313279036)
 * A comment - We used the HW2 of Sophie Krimberg
 */
public enum VehicleType {
	car(90), bus(60), bicycle(40), motorcycle(120), truck(80), tram(50), semitrailer(85);
	
	
	private int averageSpeed;

	VehicleType(int speed) {
		averageSpeed =speed/10;
	}


	public int getAverageSpeed() {
		return averageSpeed;
	}

	public String getPropertyType(VehicleType vehicleType){
		String property ="";
		if(VehicleType.car == vehicleType){
			property = "private";
		}
		else if(VehicleType.motorcycle== vehicleType){
			property = "fast";
		}
		else if(VehicleType.bicycle == vehicleType){
			property = "slow";
		}
		else if(VehicleType.truck == vehicleType){
			property = "work";
		}
		else if (VehicleType.bus == vehicleType){
			property ="public";
		}
		else if(VehicleType.tram == vehicleType){
			property = "public";
		}
		else if(VehicleType.semitrailer == vehicleType){
			property = "work";
		}
		return property;
	}

	public int getNumOfWheelseByType(VehicleType vehicleType){
		int numberOfWheels = 0;
		if(VehicleType.car == vehicleType){
			numberOfWheels = 4;
		}
		else if(VehicleType.motorcycle== vehicleType){
			numberOfWheels = 2;
		}
		else if(VehicleType.bicycle == vehicleType){
			numberOfWheels = 2;
		}
		else if(VehicleType.truck == vehicleType){
			numberOfWheels = 4;
		}
		else if (VehicleType.bus == vehicleType){
			numberOfWheels =4;
		}
		else if(VehicleType.tram == vehicleType){
			numberOfWheels = 10;
		}
		else if(VehicleType.semitrailer == vehicleType){
			numberOfWheels = 10;
		}
		return numberOfWheels;
	}
}
