package com.booking.model;

import java.util.HashMap;
import java.util.Map;

public enum CarCapacity {

    STANDARD(4),
    EXECUTIVE(4),
    LUXURY(4),
    PEOPLE_CARRIER(6),
    LUXURY_PEOPLE_CARRIER(6),
    MINIBUS(16);

    private static final Map<CarCapacity, Integer> BY_CAPACITY = new HashMap<>();
    public final Integer capacity;

    static{
        for (CarCapacity value : values()) 
            BY_CAPACITY.put(value, value.capacity);
    }

    CarCapacity(Integer reqCapacity){
        capacity = reqCapacity;
    }

    public static int valueOfCapacity(CarCapacity value){
        return BY_CAPACITY.get(value);
    }
}