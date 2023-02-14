package com.tbonegames;

import java.awt.AWTException;

public class Main {

	
	MouseHandler mHandler = new MouseHandler();
	
	public Main() throws AWTException {
		
		
		mHandler.startCircularScreen();
	}
	
	
	public static void main(String[] args) throws AWTException {

		new Main();
			
			
	}

}
