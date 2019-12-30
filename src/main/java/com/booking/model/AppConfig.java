package com.booking.model;

import java.util.ArrayList;
import com.booking.model.Car;

public class AppConfig {

    private static ArrayList<Car> carsArray;

    public AppConfig(ArrayList<Car> reqCars){
    	carsArray = reqCars;
    }

    public ArrayList<Car> getCars(){
        return carsArray;
    }

    public void setCars(ArrayList<Car> reqCars){
    	carsArray = reqCars;
    }
}