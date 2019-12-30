package com.booking.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.AppConfig;
import com.booking.model.Coordinates;

@RestController
public class ConfigController {

    @RequestMapping("/appConfig")
    public AppConfig appConfig(@RequestParam(value="pickup") String pickup, @RequestParam(value="dropoff") String dropoff,
                       @RequestParam(value="noOfPassengers", defaultValue = "1") int noOfPassengers,
                       @RequestParam(value="supplier", defaultValue = "") String supplier ) throws IOException, URISyntaxException{
    	
    	Coordinates pickupCoord = new Coordinates(Double.valueOf(pickup.substring(0, pickup.indexOf(','))), Double.valueOf(pickup.substring(pickup.indexOf(',') + 1)));
		Coordinates dropoffCoord = new Coordinates(Double.valueOf(dropoff.substring(0, pickup.indexOf(','))), Double.valueOf(dropoff.substring(pickup.indexOf(',') + 1)));

        CarManager carManager = new CarManager();
        
        
        if(supplier.equals(""))
        	//get cars from all suppliers
            return new AppConfig(carManager.getAllCars(pickupCoord, dropoffCoord, Integer.valueOf(noOfPassengers)));
        else{
            ArrayList<String> supplierId = new ArrayList<>(Arrays.asList("/" + supplier));
            //get cars from particular supplier
            return new AppConfig(carManager.getCarsFromSupplier(supplierId, pickupCoord, dropoffCoord, noOfPassengers));
        }
    }
}