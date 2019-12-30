package com.booking.services;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import com.booking.model.Coordinates;
import com.booking.model.Response;
import com.google.gson.Gson;

public class ApiConnector {
	public Response callApi(String myUrl, Coordinates pickupCoord, Coordinates dropoffCoord) throws IOException, URISyntaxException{
		
		String reqParameters = "pickup=" + pickupCoord.getLatitude() + "," + pickupCoord.getLongitude() 
							+ "&dropoff=" + dropoffCoord.getLatitude() + "," + dropoffCoord.getLongitude();
		
		URI uri = new URI("https", "techtest.rideways.com", myUrl, reqParameters, "");
		
		URL url = uri.toURL();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		int status = connection.getResponseCode();
		//check connection status
		if(status == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer content = new StringBuffer();
			content.append(in.readLine());
		
			Gson gson = new Gson();
			Response response = gson.fromJson(content.toString(), Response.class);
			//System.out.println(content.toString());
			in.close();
			return response;
		}
		return null;
	}
}
