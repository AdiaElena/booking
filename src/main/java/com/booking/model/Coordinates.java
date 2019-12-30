package com.booking.model;

public class Coordinates {
	private double latitude;
	private double longitude;
	
	public Coordinates(double reqLatitude, double reqLongitude) {
		latitude = reqLatitude;
		longitude = reqLongitude;
	}
	
	//accessor methods
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
}
