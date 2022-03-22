package me.datashibe.Marketplace.FrameDesigner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameDesigner {
	
	private static JFrame frame;
	private static int currentX = 0;
	private static int currentY = 50;
	private static String mode;
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		
		
		JLabel headerLabel = new JLabel("Marketplace");
		frame.add(headerLabel);
		headerLabel.setBounds(10, 10, 200, 40);
		headerLabel.setFont(new Font("MonoSpace", Font.PLAIN, 30));
		
		JLabel userNameLabel = new JLabel("monke");
		frame.add(userNameLabel);
		userNameLabel.setBounds(700, 20, 100, 30);

		JLabel moneyLabel = new JLabel("$14,000");
		frame.add(moneyLabel);
		moneyLabel.setBounds(800, 20, 200, 30);
		
		JLabel garageLabel = new JLabel("Garage");
		frame.add(garageLabel);
		garageLabel.setBounds(10, 50, 100, 40);
		
		JLabel usedCarsLabel = new JLabel("Used Cars");
		frame.add(usedCarsLabel);
		usedCarsLabel.setBounds(10, 100, 100, 40);
		
		JLabel newCarsLabel = new JLabel("New Cars");
		frame.add(newCarsLabel);
		newCarsLabel.setBounds(10, 150, 100, 40);
		
		JLabel onlineCarsLabel = new JLabel("Online Cars");
		frame.add(onlineCarsLabel);
		onlineCarsLabel.setBounds(10, 200, 100, 40);
		
		JLabel friendsLabel = new JLabel("Friends");
		frame.add(friendsLabel);
		friendsLabel.setBounds(10, 250, 100, 40);
		
		
		
		try {
			BufferedImage bufferedImage = ImageIO.read(new File("/Users/Niklas/Desktop/2.jpg"));
			int fixedWidth = 500;
			float nh = fixedWidth / ((float) bufferedImage.getWidth() / (float) bufferedImage.getHeight());
			System.out.println(nh);
			JLabel imageLabel = new JLabel(new ImageIcon(bufferedImage.getScaledInstance(fixedWidth, (int) nh, java.awt.Image.SCALE_SMOOTH)));
			frame.add(imageLabel);
			imageLabel.setBounds(125, 75, fixedWidth, (int) nh);
			
			JButton buyButton = new JButton("Buy");
			frame.add(buyButton);
			buyButton.setBounds(125 + fixedWidth + 20, 200, 100, 50);
			
			JLabel prizeLabel = new JLabel("$14,000");
			frame.add(prizeLabel);
			prizeLabel.setBounds(125 + fixedWidth + 21, 170, 100, 30);
			prizeLabel.setFont(new Font(prizeLabel.getFont().getName(), Font.PLAIN, 15));
			prizeLabel.setForeground(Color.DARK_GRAY);
			
			JLabel nameLabel = new JLabel("MX-5");
			frame.add(nameLabel);
			nameLabel.setBounds(125 + fixedWidth + 21, 125, 400, 40);
			nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, 25));
			nameLabel.setForeground(Color.gray);
			
			JLabel manifacturerLabel = new JLabel("Mazda");
			frame.add(manifacturerLabel);
			manifacturerLabel.setBounds(125 + fixedWidth + 20, 85, 400, 40);
			manifacturerLabel.setFont(new Font(manifacturerLabel.getFont().getName(), Font.PLAIN, 50));
			manifacturerLabel.setForeground(Color.gray);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}