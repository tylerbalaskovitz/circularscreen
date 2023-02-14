package com.tbonegames;

import java.awt.AWTException;
import java.awt.Dimension;
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
	
	public void getCurrentMouseCoordinates() throws AWTException { 
		
		//gets the resolution of the screen via AWT and the Dimension class
		size = Toolkit.getDefaultToolkit().getScreenSize();
		
		//used to calibrate the screen for the first time when the initial program is being ran. 
		//The screen size will be used then negated to get the rightMostX, and leftMostX
		
		rightMostX = ((int)size.getWidth()-1);
		leftMostX = (-(int)size.getWidth());
		
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
	}
}
