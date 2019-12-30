package com.booking.services;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import com.booking.model.Car;
import com.booking.model.Coordinates;

public class ResultPrinter {

	private CarManager carManager = new CarManager();
	
	public void printCarsFromAllSuppliers(int noOfPassengers, Coordinates pickupCoord, Coordinates dropoffCoord) throws IOException, URISyntaxException {
		
        ArrayList<Car> allCars = carManager.getAllCars(pickupCoord, dropoffCoord, noOfPassengers);
        
        for(Car currentCar : allCars)
        	System.out.println(currentCar.getSupplier() + " - " + currentCar.getType() + " - " + currentCar.getPrice());
	}

	public void printCarsFromCertainSupplier(int noOfPassengers, Coordinates pickupCoord,
			Coordinates dropoffCoord, String supplier) throws IOException, URISyntaxException {
		
        ArrayList<String> supplierID = new ArrayList<>(Arrays.asList("/" + supplier));
        ArrayList<Car> carsFromSupplier = carManager.getCarsFromSupplier(supplierID, pickupCoord, dropoffCoord, noOfPassengers);
      //check if supplier has available cars
        if(!carsFromSupplier.isEmpty()){
        	System.out.println("Available cars from " + supplier + ":");
        	System.out.println();
            for(Car currentCar : carsFromSupplier)
            	System.out.println(currentCar.getType() + " - " + currentCar.getPrice());
        }
        else
        	System.out.println("Did not find available cars from supplier " + supplier);
	}
}
