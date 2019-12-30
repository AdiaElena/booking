package com.booking.services;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.booking.model.Car;
import com.booking.model.CarCapacity;
import com.booking.model.Coordinates;
import com.booking.model.Response;

public class CarManager {
	
	public ArrayList<Car> getAllCars(Coordinates pickupCoord,
			Coordinates dropoffCoord, int noOfPassengers) throws IOException, URISyntaxException {
		
		ArrayList<Car> cars = new ArrayList<>();
        ArrayList<String> possibleSuppliers = new ArrayList<>(Arrays.asList("/dave", "/eric", "/jeff"));
        HashMap<String, ArrayList<Car>> supplierWithCars = getSuppliers(possibleSuppliers, pickupCoord, dropoffCoord, noOfPassengers);
        
        for(CarCapacity currentCarCapacity : CarCapacity.values()){
            try{
                int minimumPrice = 999999999;
                Car foundCar = null;
                for(String currSupplier: supplierWithCars.keySet())
                {
                	ArrayList<Car> results = supplierWithCars.get(currSupplier);
                	for(Car currCar : results)
                	{
                		if(currCar.getType().equals(currentCarCapacity.name()) && minimumPrice > currCar.getPrice())
                		{
                			minimumPrice = currCar.getPrice();
                			foundCar = currCar;
                		}
                	}
                }
                if(foundCar != null)
                {
                	cars.add(foundCar);
                }
            }
            catch (NoSuchElementException e){
            }
        }
        return cars;
	}
	
	public ArrayList<Car> getCarsFromSupplier(ArrayList<String> supplierIds,
    		Coordinates pickupCoord, Coordinates dropoffCoord,
			int noOfPassengers) throws IOException, URISyntaxException{
		
    	CarManager carManager = new CarManager();
    	
		HashMap<String, ArrayList<Car>> suppliers = carManager.getSuppliers(supplierIds, pickupCoord, dropoffCoord, noOfPassengers);
		ArrayList<Car> cars = new ArrayList<>();

		if(!suppliers.isEmpty()){
			for(String currentId : supplierIds){
				
				StringBuilder supplierId = new StringBuilder(currentId);
				supplierId.deleteCharAt(0);
				currentId = supplierId.toString().toUpperCase();
				
				cars = (ArrayList<Car>) suppliers.get(currentId).stream().sorted(Comparator.comparing(Car::getPrice).reversed()).collect(Collectors.toList());
				
				if(!cars.isEmpty())
					return cars;
				else{
					System.out.println("No available cars.");
				}
			}
		}
		return cars;
	}
	
	private HashMap<String, ArrayList<Car>> getSuppliers(ArrayList<String> supplierIds, Coordinates pickupCoord,
			Coordinates dropoffCoord, int noOfPassengers) throws IOException, URISyntaxException{
		
		HashMap<String, ArrayList<Car>> supplierWithCars = new HashMap<>();
		ArrayList<Response> responses = new ArrayList<>();
		ApiConnector apiConnector = new ApiConnector();
		
		for(String currentId : supplierIds)
			responses.add(apiConnector.callApi(currentId, pickupCoord, dropoffCoord));

		for(Response currentResponse : responses){
			ArrayList<Car> cars = new ArrayList<>();
			
			if(currentResponse != null){
				for(HashMap<String, String> myResponse : currentResponse.getOptions()){
					String carType = myResponse.get("car_type");
					
					if(CarCapacity.valueOfCapacity(CarCapacity.valueOf(carType)) >= noOfPassengers){
						Car currentCar = new Car(carType, CarCapacity.valueOfCapacity(CarCapacity.valueOf(carType)),
								Integer.valueOf(myResponse.get("price")), currentResponse.getSupplier());
						cars.add(currentCar);
					}
				}
				supplierWithCars.put(currentResponse.getSupplier(), cars);
			}
		}
		return supplierWithCars;
	}
}
