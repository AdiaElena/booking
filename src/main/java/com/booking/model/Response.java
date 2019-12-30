package com.booking.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {
	private String supplier_id;
	private String pickup;
	private String dropoff;
	private ArrayList<HashMap<String, String>> options;
	
	public Response(String reqSupplier, String reqPickup, String reqDropoff, ArrayList<HashMap<String, String>> reqOptions) {
		supplier_id = reqSupplier;
		pickup = reqPickup;
		dropoff = reqDropoff;
		options = reqOptions;
	}
	
	//getter methods
	public String getSupplier() {
		return supplier_id;
	}
	
	public ArrayList<HashMap<String, String>> getOptions(){
		return options;
	}
}
