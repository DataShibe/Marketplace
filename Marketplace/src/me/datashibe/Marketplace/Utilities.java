package me.datashibe.Marketplace;

public class Utilities {

	public static Car checkIfCarIsPreloaded(int id) {
		for(int i = 0; i < Marketplace.cars.size(); i++) {
			if(Marketplace.cars.get(i).id == id) return Marketplace.cars.get(i);
		}
		
		return null;
	}
	
}
