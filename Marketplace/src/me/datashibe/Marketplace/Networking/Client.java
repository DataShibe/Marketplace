package me.datashibe.Marketplace.Networking;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import me.datashibe.Marketplace.Car;
import me.datashibe.Marketplace.Marketplace;

public class Client {

	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	private Socket socket;
	
	public void connect(String ip, int port) {
		
		try {
			
			socket = new Socket(ip, port);
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//sendMessage("connect");
			//sendMessage("login monke:banan");
			
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public String sendMessage(String message) {
		printWriter.println(message);
		try { 
			return bufferedReader.readLine(); 
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
	
	public void disconnect() {
		try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
	}
	
	public Car getCar(int id) {
		Car car;
		ImageIcon picture = getPicture(id);
		
		String[] lines = getFile(id).split("/n");
		
		System.out.println(String.join("\n", lines));
		
		String name = lines[1].split(": ")[1];
		String manifacturer = lines[2].split(": ")[1];
		String year = lines[4].split(": ")[1];
		String country = lines[3].split(": ")[1];
		String engine = lines[6].split(": ")[1];
		String prize = lines[5].split(": ")[1];
		String torque = lines[7].split(": ")[1];
		String hp = lines[8].split(": ")[1];
		String peakRPM = lines[9].split(": ")[1];
		String topSpeed = lines[10].split(": ")[1];
		
		car = new Car(id, name, manifacturer, year, country, prize, engine, torque, hp, peakRPM, topSpeed, picture);
		Marketplace.cars.add(car);
		return car;
	}
	
	public String getFile(int id) {
		String file = "";
		try { file= bufferedReader.readLine(); } catch(Exception e) { e.printStackTrace(); }
		return file;
	}
	
	public ImageIcon getPicture(int id) {
		printWriter.println("car-get " + id);
		
		System.out.println("reading");
		
		byte[] sizeArray = new byte[4];
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream();
			inputStream.read(sizeArray);
			int size = ByteBuffer.wrap(sizeArray).asIntBuffer().get();
			
			byte[] imageArray = new byte[size];
			inputStream.read(imageArray);
			
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageArray));
			
			System.out.println("done reading");
			
			return new ImageIcon(image);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
