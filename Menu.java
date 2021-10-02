package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game; 
	private Main main; 
	private Handler handler; 
	private HUD hud; 
	private Random r = new Random(); 
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler; 
		this.hud = hud; 
	}
	
	public Menu(Main main, Handler handler) {
		this.main = main;
		this.handler = handler; 
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//Play Escape the Enemy button
			if (mouseOver(mx, my, 100, 160, 200, 64)){
				game.gameState = STATE.Select; 
				return; 
			}
			
			//Help button
			if (mouseOver(mx, my, 100, 260, 200, 64)){
				game.gameState = STATE.Help; 
			}
			
			//Quit button 
			if (mouseOver(mx, my, 350, 260, 200, 64)){
				System.exit(1); 
			}
		}
		
		if(game.gameState == STATE.Select) {
			//Normal button
			if (mouseOver(mx, my, 210, 150, 200, 64)){
				game.gameState = STATE.Game; 
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32,ID.Player, handler)); 
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
				game.diff = 0; 
			}
			
			//Hard button 
			if (mouseOver(mx, my, 210, 250, 200, 64)){
				game.gameState = STATE.Game; 
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32,ID.Player, handler)); 
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
				game.diff = 1; 
			}
			
			//Back button 
			if (mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu; 
				return; 
			}
		}
			
		//Back button for help 
		if(game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu; 
				return; 
			}
		}
		
		//Try again button for Game over 
		if(game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Game; 
				hud.setLevel(1);
				hud.setScore(0);
				if(game.diff == 0) {
					handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32,ID.Player, handler)); 
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
				} else if(game.diff == 1) {
					handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32,ID.Player, handler)); 
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
				}
			}
		}
		
		//Back to Menu button for Game over 
		if(game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Menu; 
				return; 
				}
			}
		
		//Play Brick breaker button
		if (mouseOver(mx, my, 350, 160, 200, 64)){
			Main.tick(); 
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true; 
			} else { 
				return false; 
			}
		} else {
			return false; 
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50); 
			Font fnt2 = new Font("arial", 1, 15); 
			Font fnt3 = new Font("arial", 1, 30); 

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("PLYR Game Center", 110, 70);
			g.setFont(fnt2);
			g.drawString("Menu", 291, 110);
			g.setColor(Color.red);
			g.drawString("Select one of the following options", 186, 130);
			g.setColor(Color.white);
			g.drawRect(100, 160, 200, 64);
			g.drawString("Play Escape the Enemy", 115, 195);
			
			g.setColor(Color.white);
			g.drawRect(350, 160, 200, 64);
			g.drawString("Play Brick Breaker", 380, 195);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawRect(100, 260, 200, 64);
			g.drawString("Help", 167, 300);
			
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawRect(350, 260, 200, 64);
			g.drawString("Quit", 416, 300);
			
			g.setFont(fnt2);
			g.setColor(Color.CYAN);
			g.drawString("One of Ameer Husary's Projects", 194, 370);
		} else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50); 
			Font fnt3 = new Font("arial", 1, 30); 
			Font fnt2 = new Font("arial", 1, 15); 
			Font fnt4 = new Font("arial", 1, 14); 
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 260, 70);
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.setColor(Color.red);
			g.drawString("Escape the Enemy", 250, 107);
			g.setColor(Color.white);
			g.drawString("Use arrow keys to move the player up, down, left, and right", 100, 130);
			g.drawString("Objective: Dodge the enemies and SURVIVE!", 140, 150);
			g.setColor(Color.red);
			g.drawString("Brick Breaker", 263, 200);
			g.setColor(Color.white);
			g.setFont(fnt4);
			g.drawString("Move the PADDLE side-to-side using the left and right arrow keys to hit the BALL", 40, 220);
			g.drawString("Objective: ", 270, 240); 
			g.drawString("eliminate all of the BRICKS at the top of the screen by hitting them with the BALL.", 38, 260);
			g.drawString("But, if the ball hits the bottom ENCLOSURE, the player loses and the game ends!", 38, 280);

			
			
			g.setFont(fnt3);
			g.drawString("Back", 270, 390);
			
		} else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 60); 
			Font fnt2 = new Font("arial", 1, 20); 
			Font fnt3 = new Font("arial", 1, 30); 
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", 140, 70);
			g.setFont(fnt2);
			g.setColor(Color.red);
			g.drawString("You lost with a score of: " + hud.getScore(), 186, 200);
			g.setColor(Color.cyan);
			g.setFont(fnt2);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Back to Menu", 247, 290);
			g.setFont(fnt3);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try again", 245, 390);
		} else if(game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50); 
			Font fnt3 = new Font("arial", 1, 30); 

			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Select Difficulty", 140, 70);
			
			g.setColor(Color.white);
			g.setFont(fnt3);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 265, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 275, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 275, 390);
			
		}
		
	}
}
