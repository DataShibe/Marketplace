package me.datashibe.Marketplace;

import java.util.ArrayList;

import me.datashibe.Marketplace.Networking.Client;
import me.datashibe.Marketplace.Visuals.FrameHandler;

public class Marketplace {

	public static Client client;
	public static ArrayList<Car> cars;
	
	public static void main(String[] args) {		
		cars = new ArrayList<Car>();

		client = new Client();
		client.connect("localhost", 5252);

		FrameHandler frameHandler = new FrameHandler();
		frameHandler.handle();

	}
	
}
