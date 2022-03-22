package me.datashibe.Marketplace.Visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.datashibe.Marketplace.Car;
import me.datashibe.Marketplace.Marketplace;
import me.datashibe.Marketplace.Networking.Client;

public class GameFrame {

	private static int currentX = 0;
	private static int currentY = 50;
	private static String mode = "Garage";
	private static JFrame frame;
	private static Car car;
	
	public static void run(Client client) {
		frame = FrameHandler.frame;
		
		frame.setContentPane(new JPanel() {
			public void paintComponent(Graphics g) {
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(0, 50, 1000, 50);
				g.drawLine(0, 100, 100, 100);
				g.drawLine(0, 150, 100, 150);
				g.drawLine(0, 200, 100, 200);
				g.drawLine(0, 250, 100, 250);
				g.drawLine(100, 50, 100, 1000);
				
				g.fillRect(currentX, currentY, 100, 50);
			}
		});
		frame.getContentPane().setLayout(null);
		
		repaint();
		updateCars();
		
		/*
		
		
		*/
		
	}
	
	private static void updateCars() {
		switch(mode) {
		
		case "Garage":
			renderCars("list garage");
			break;
			
		case "Used":
			renderCars("list used-cars");
			break;
			
		case "New":
			renderCars("list new-cars");
			break;
			
		case "Friends":
			break;
		}
		
	}
	
	private static void repaint() {
		frame.getContentPane().removeAll();
		frame.getContentPane().invalidate();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
		JLabel headerLabel = new JLabel("Marketplace");
		frame.add(headerLabel);
		headerLabel.setBounds(10, 10, 200, 40);
		headerLabel.setFont(new Font("MonoSpace", Font.PLAIN, 30));
		
		JLabel userNameLabel = new JLabel("monke");
		frame.add(userNameLabel);
		userNameLabel.setBounds(700, 20, 100, 30);
		
		JLabel moneyLabel = new JLabel(Marketplace.client.sendMessage("user-get money"));
		frame.add(moneyLabel);
		moneyLabel.setBounds(800, 20, 200, 30);
		
		JLabel garageLabel = new JLabel("Garage");
		frame.add(garageLabel);
		garageLabel.setBounds(10, 50, 100, 40);
		garageLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				currentX = 0;
				currentY = 50;
				
				mode = "Garage";
				updateCars();
				
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		JLabel usedCarsLabel = new JLabel("Used Cars");
		frame.add(usedCarsLabel);
		usedCarsLabel.setBounds(10, 100, 100, 40);
		usedCarsLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				currentX = 0;
				currentY = 100;
				
				mode = "Used";
				updateCars();
				
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		JLabel newCarsLabel = new JLabel("New Cars");
		frame.add(newCarsLabel);
		newCarsLabel.setBounds(10, 150, 100, 40);
		newCarsLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				currentX = 0;
				currentY = 150;
				
				mode = "New";
				updateCars();
				
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		JLabel onlineCarsLabel = new JLabel("Online Cars");
		frame.add(onlineCarsLabel);
		onlineCarsLabel.setBounds(10, 200, 100, 40);
		onlineCarsLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				currentX = 0;
				currentY = 200;
				
				mode = "Online";
				updateCars();
				
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		JLabel friendsLabel = new JLabel("Friends");
		frame.add(friendsLabel);
		friendsLabel.setBounds(10, 250, 100, 40);
		friendsLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				currentX = 0;
				currentY = 250;
				
				mode = "Friends";
				updateCars();
				
				frame.getContentPane().repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
	}
	
	private static void renderCars(String command) {
		repaint();
		
		String responce = Marketplace.client.sendMessage(command);
		
		if(responce.equals("[]")) {
			return;
		}
		
		String[] cars = responce.replace("[", "").replace("]", "").split(", ");
		int[] ids = new int[cars.length];
		for(int i = 0; i < cars.length; i++) ids[i] = Integer.parseInt(cars[i]);
		
		for(int i = 0; i < ids.length; i++) {
			car = me.datashibe.Marketplace.Utilities.checkIfCarIsPreloaded(ids[i]);
			if(car == null) car = Marketplace.client.getCar(ids[i]);
			
			ImageIcon miatIcon = new ImageIcon(car.picture.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
			JLabel imageLabel = new JLabel(miatIcon);
			frame.add(imageLabel);
			imageLabel.setBounds(140 + (i * 100) + (i * 20), 75, 100, 100);
			imageLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					repaint();
					buyScreen(me.datashibe.Marketplace.Utilities.checkIfCarIsPreloaded(ids[(imageLabel.getX() - 140) / 120]), !mode.equals("Garage"));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
				
			});
			
			JLabel carNameLabel = new JLabel(car.name);
			frame.add(carNameLabel);
			carNameLabel.setBounds(140 + (i * 100) + (i * 20), 185, 100, 20);
			carNameLabel.setForeground(Color.gray);
		}
		
	}

	public static void buyScreen(Car car, boolean buy) {
		int fixedWidth = 500;
		float nh = fixedWidth / ((float) car.picture.getIconWidth() / (float) car.picture.getIconHeight());
		JLabel imageLabel = new JLabel(new ImageIcon(car.picture.getImage().getScaledInstance(fixedWidth, (int) nh, java.awt.Image.SCALE_SMOOTH)));
		frame.add(imageLabel);
		imageLabel.setBounds(125, 75, fixedWidth, (int) nh);
		
		JButton buyButton = new JButton("Buy");
		if(!buy) buyButton.setText("Sell");
		frame.add(buyButton);
		buyButton.setBounds(125 + fixedWidth + 20, 200, 100, 50);
		buyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(buy) Marketplace.client.sendMessage("buy " + car.id);
				else Marketplace.client.sendMessage("sell " + car.id);
				mode = "Garage";
				repaint();
			}
			
		});
		
		JLabel prizeLabel = new JLabel(car.prize);
		frame.add(prizeLabel);
		prizeLabel.setBounds(125 + fixedWidth + 21, 170, 100, 30);
		prizeLabel.setFont(new Font(prizeLabel.getFont().getName(), Font.PLAIN, 15));
		prizeLabel.setForeground(Color.DARK_GRAY);
		
		JLabel nameLabel = new JLabel(car.name);
		frame.add(nameLabel);
		nameLabel.setBounds(125 + fixedWidth + 21, 125, 400, 40);
		nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, 25));
		nameLabel.setForeground(Color.gray);
		
		JLabel manifacturerLabel = new JLabel(car.manifacturer);
		frame.add(manifacturerLabel);
		manifacturerLabel.setBounds(125 + fixedWidth + 20, 85, 400, 50);
		manifacturerLabel.setFont(new Font(manifacturerLabel.getFont().getName(), Font.PLAIN, 50));
		manifacturerLabel.setForeground(Color.gray);
	
		// add other shit
	}
	
}
