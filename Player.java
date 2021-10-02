package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random(); 
	Handler handler; 

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler; 
//		velX = r.nextInt(5) + 1;  
//		velY = r.nextInt(5);  
		// TODO Auto-generated constructor stub
	} 
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y,32,32); 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;  
		y += velY; 
		
		x = Game.clamp(x, 0, Game.WIDTH - 45); 
		y = Game.clamp(y, 0, Game.HEIGHT - 70); 
		
//		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));

		
		collision(); 
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i); 
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy) {//tempObject is now BasicEnemy 
				if(getBounds().intersects(tempObject.getBounds())) {
					//Collision code: 
					HUD.HEALTH -= 2; 
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g; 
		// TODO Auto-generated method stub
		g.setColor(Color.white);
//		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 32, 32);
	}

}
