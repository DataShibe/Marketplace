package me.datashibe.Marketplace.Visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.datashibe.Marketplace.Marketplace;

public class Login {
	
	public static void login(boolean register) {
	
		JTextField usernameField = new JTextField();
		usernameField.setText("Username");
		fuckingGrayTextField(usernameField);
		
		FrameHandler.frame.add(usernameField);
		usernameField.setBounds(175, 100, 150, 35);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setText("Password");
		fuckingGrayTextField(passwordField);
		
		FrameHandler.frame.add(passwordField);
		passwordField.setBounds(175,  140, 150, 35);
		
		JLabel wrongLabel = new JLabel();
		FrameHandler.frame.add(wrongLabel);
		wrongLabel.setBounds(155, 210, 250, 50);
		wrongLabel.setForeground(Color.red);
		
		JButton loginButton = new JButton("Login");
		if(register) loginButton.setText("Register");
		loginButton.setBackground(Color.blue);
		FrameHandler.frame.add(loginButton);
		loginButton.setBounds(250, 180, 75, 40);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String prefix = "";
				
				if(!register) prefix = "login";
				else prefix = "register";
				
				String responce = Marketplace.client.sendMessage(prefix + " " + usernameField.getText() + " " + passwordField.getText());
				if(responce.equals(prefix + " confirm")) {
					GameFrame.run(Marketplace.client);
				} else if(responce.equals("incorrect login") || responce.equals("user already exists")) {
					
					if(!register) wrongLabel.setText("Incorrect username or password");
					else wrongLabel.setText("User is already existing");
					
					passwordField.setText("Username");
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(Color.gray);
				}
			}
			
		});
		
		JLabel headerLabel = new JLabel("Marketplace");
		FrameHandler.frame.add(headerLabel);
		headerLabel.setBounds(160, 25, 200, 100);
		headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 30));
		
		JLabel registerLabel = new JLabel("Register");
		if(register) registerLabel.setText("Login");
		FrameHandler.frame.add(registerLabel);
		registerLabel.setBounds(185, 175, 65, 50);
		registerLabel.setForeground(Color.blue);
		registerLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				FrameHandler.frame.getContentPane().removeAll();
				FrameHandler.frame.getContentPane().invalidate();
				FrameHandler.frame.getContentPane().revalidate();
				FrameHandler.frame.getContentPane().repaint();
				
				login(!register);
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
	}
	
	public static void fuckingGrayTextField(JTextField textField) {
		String text = textField.getText();
		textField.setForeground(Color.gray);
		
		if(textField instanceof JPasswordField) {
			((JPasswordField) textField).setEchoChar((char) 0);
		}
		
		textField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) { textField.setCaretPosition(0); }

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(textField.getText().equals(text) && textField.getForeground() == Color.gray) {
					
					if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						textField.setCaretPosition(0);
						return;
					}
					
					textField.setText("");
					textField.setForeground(Color.black);
					
					if(textField instanceof JPasswordField) {
						((JPasswordField) textField).setEchoChar('●');
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(textField.getText().equals(text) && textField.getForeground() == Color.gray) {
					
					if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						textField.setCaretPosition(0);
						return;
					}
				}
				
				if(textField.getText().equals("")) {
					textField.setText(text);
					textField.setForeground(Color.gray);
					textField.setCaretPosition(0);
					
					if(textField instanceof JPasswordField) {
						((JPasswordField) textField).setEchoChar((char) 0);
					}
				}
			}
		});
	}
	
}