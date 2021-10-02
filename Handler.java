package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	public int spd = 5; 
	LinkedList<GameObject> object = new LinkedList<GameObject>(); 

	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i); 
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i); 
			tempObject.render(g); 
		}
	}
	
	public void clearEnemys() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i); 
			if(tempObject.getID() == ID.Player) {
				object.clear();
				if(Game.gameState != Game.STATE.End) addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object); 
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object); 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
