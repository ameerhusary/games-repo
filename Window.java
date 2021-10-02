package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 969472172981425819L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(); //frame of the window
		//window specs: 
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		//the following will allow you to close the window using the red x button. 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//we don't want the game to be resizable 
		frame.setResizable(false);  
		//if we don't do the following then the window will always pop up in the left side of the screen
		frame.setLocationRelativeTo(null);
		//adding the game class to our frame
		frame.add(game); 
		//set the frame visible to see it
		frame.setVisible(true);
		game.start();
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
