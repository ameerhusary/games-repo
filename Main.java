//The following game code for Brick Breaker was inspired and built upon the code by Awais Mirza YouTube Channel 

package com.tutorial.main;
import javax.swing.JFrame;

import com.tutorial.main.Game.STATE;

public class Main{
	public static void tick() {
			JFrame obj = new JFrame(); 
	        Gameplay gamePlay = new Gameplay(); 
	        obj.setBounds(10, 10, 700, 600);
	        obj.setTitle("Brick Breaker");
	        obj.setResizable(false);
	        obj.setVisible(true);
	        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        obj.add(gamePlay); 
	}
}