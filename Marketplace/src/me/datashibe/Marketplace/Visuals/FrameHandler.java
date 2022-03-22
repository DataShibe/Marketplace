package me.datashibe.Marketplace.Visuals;

import javax.swing.JFrame;

import me.datashibe.Marketplace.Marketplace;

public class FrameHandler {
	
	public static JFrame frame;
	
	public FrameHandler() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	}
	
	public void handle() {
		Login.login(false);
		//GameFrame.run(Marketplace.client);
	}
}