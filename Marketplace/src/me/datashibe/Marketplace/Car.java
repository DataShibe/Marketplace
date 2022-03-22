package me.datashibe.Marketplace;

import javax.swing.ImageIcon;

public class Car {

	public ImageIcon picture;
	
	public String name;
	public String manifacturer;
	public String year;
	public String country;
	public String engine;
	public int id;
	public String prize;
	public String torque;
	public String hp;
	public String peakRPM;
	public String topSpeed;
	
	public Car(int id, String name, String manifacturer, String year, String country, String prize, String engine, String torque, String hp, String peakRPM, String topSpeed, ImageIcon picture) {
		this.id = id;
		this.name = name;
		this.manifacturer = manifacturer; 
		this.year = manifacturer; 
		this.country = country;
		this.prize = prize;
		this.engine = engine;
		this.torque = torque;
		this.hp = hp;
		this.peakRPM = peakRPM;
		this.topSpeed = topSpeed;
		
		this.picture = picture;
	}
	
}
