package com.tbonegames;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

	public int leftMostX = -1920;
	public int rightMostX = 1919;
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
	
	public void getCurrentMouseCoordinates() throws AWTException { 
		robot = new Robot();
		while (true) {
		pointerLocation = MouseInfo.getPointerInfo().getLocation();
		currentLocationX = (int)pointerLocation.getLocation().getX();
		currentLocationY = (int)pointerLocation.getLocation().getY();
		
		if (currentLocationX <= leftMostX) {
			robot.mouseMove((rightMostX - 1), currentLocationY);
		}
		if (currentLocationX >= rightMostX) {
			robot.mouseMove((leftMostX + 1), currentLocationY);
		}
		
		System.out.println("Current X:" + currentLocationX + "Y:" + currentLocationY);
		}
	}

}
