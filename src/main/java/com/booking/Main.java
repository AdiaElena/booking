package com.booking;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.logging.Logger;

import com.booking.model.Coordinates;
import com.booking.services.ResultPrinter;

public class Main {
    
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
	  
	    ResultPrinter resultPrinter = new ResultPrinter();
	    Logger logger = Logger.getLogger(Main.class.getName());
		try{
			if(args.length < 4)
				throw new IllegalArgumentException("Not enough arguments.");

			Coordinates pickupCoord = new Coordinates(Double.valueOf(args[0]), Double.valueOf(args[1]));
			Coordinates dropoffCoord = new Coordinates(Double.valueOf(args[2]), Double.valueOf(args[3]));
	            
			//input: only coordinates
	        if(args.length == 4)
	        	resultPrinter.printCarsFromAllSuppliers(0, pickupCoord, dropoffCoord);
	        
	        //input: coordinates + noOfPassengers
	        else if(args.length == 5)
	        	resultPrinter.printCarsFromAllSuppliers(Integer.parseInt(args[4]), pickupCoord, dropoffCoord);

	        //input: supplier also specified   
	        else if(args.length == 6){
	        	if(!args[5].equals("dave") && !args[5].equals("jeff") & !args[5].equals("eric"))
	        		throw new InputMismatchException("Please enter a valid name");

	        	resultPrinter.printCarsFromCertainSupplier(Integer.parseInt(args[4]), pickupCoord, dropoffCoord, args[5]);
	        }
	    }
		catch(IllegalArgumentException e){
        	logger.info("IllegalArgumentException: " + e.getMessage());
        }
        catch(InputMismatchException e){
        	logger.info("InputMismatchException: " + e.getMessage());
        }
        catch(NullPointerException e){
        	logger.info("NullPointerException: " + e.getMessage());
        }
	}
}
