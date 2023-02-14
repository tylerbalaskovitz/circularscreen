package com.tbonegames;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener, Runnable {
	public boolean robotRunning = false;
	Dimension size;
	Thread mainThread;
	int resolutionSize;
	public int leftMostX;
	public int rightMostX;
	Robot robot;
	public Point pointerLocation;
	public int currentLocationX;
	public int currentLocationY;
	public int numberOfScreens;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void startCircularScreen() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void getNumberOfDisplayDevices() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsDevice[] devices = env.getScreenDevices();
		
		
		
		numberOfScreens = devices.length;
		for (int i = 0; i < numberOfScreens; i++) {
		devices[i].getDefaultConfiguration().getBounds();
		if (i == 0) {
			leftMostX = devices[i].getDefaultConfiguration().getBounds().x;
		}
		if (i == (numberOfScreens - 1)) {
			rightMostX = (devices[i].getDefaultConfiguration().getBounds().x + (devices[i].getDefaultConfiguration().getBounds().width)-1);
		}
		}
		
	}
	
	public void getCurrentMouseCoordinates() throws AWTException { 
		
		//gets the resolution of the screen via AWT and the Dimension class
		size = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		if (robotRunning == false) {
			robot = new Robot();
			robotRunning = true;
		}
		pointerLocation = MouseInfo.getPointerInfo().getLocation();
		currentLocationX = (int)pointerLocation.getLocation().getX();
		currentLocationY = (int)pointerLocation.getLocation().getY();
		
		if (currentLocationX <= leftMostX) {
			robot.mouseMove((rightMostX - 1), currentLocationY);
		}
		if (currentLocationX >= rightMostX) {
			robot.mouseMove((leftMostX + 1), currentLocationY);
		}
		
		//System.out.println("Current X:" + currentLocationX + "Y:" + currentLocationY);
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		try {
		while (mainThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				try {
					getCurrentMouseCoordinates();
				} catch (AWTException e) {
					e.printStackTrace();
				}
				delta--;
			}
			
			if (timer >= 1000000000) {
				timer = 0;
			}
		
		}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
