package com.tbonegames;


public class Main {

	
	MouseHandler mHandler = new MouseHandler();
	
	public Main() {
		
		mHandler.getNumberOfDisplayDevices();
		mHandler.startCircularScreen();
		
	}
	
	
	public static void main(String[] args) {

		new Main();
			
			
	}

}
