//run this class in order to play the game. 

//The following game code for Escape the Enemy was inspired and built upon the code by RealTutsGML YouTube Channel


package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	/**
	 * HI
	 * this is AMEER's work
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	private Thread thread; 
	private boolean running = false; 
	private Random r; 
	private Handler handler; 
	public static boolean paused = false; 
	private HUD hud; 
	private Menu menu; 
	private Spawn spawner; 
	private Shop shop; 
	public int diff = 0; 
	public enum STATE{
		Menu, 
		Help, 
		End, 
		Shop, 
		Select, 
		Game
	};
	public static STATE gameState = STATE.Menu; 
	
	
	public Game(){
		handler = new Handler(); 
		hud = new HUD(); 
		shop = new Shop(handler, hud); 
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(shop);
		new Window(WIDTH, HEIGHT, "Let's Build A Game!", this); 
		spawner = new Spawn(handler, hud, this);  
		r = new Random(); 
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2 - 32,HEIGHT/2 - 32,ID.Player, handler)); 
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
		}
//		for(int i = 0; i < 50; i++) {
//			handler.addObject(new Player(r.nextInt(WIDTH),r.nextInt(HEIGHT),ID.Player)); 
//		}
//		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		//you can add a for loop here i < 20 to create more ;)
//		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT),ID.BasicEnemy, handler));  

//		handler.addObject(new Player(WIDTH/2 + 64,HEIGHT/2 - 32,ID.Player2)); 
	}

	public synchronized void start() {
		thread = new Thread(this); 
		thread.start();
		running = true; 
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false; 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus(); //I don't have to click in order to move anymore
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; 
		double ns = 1000000000/ amountOfTicks; 
		double delta = 0; 
		long timer = System.currentTimeMillis(); 
		int frames = 0; 
		while(running) {
			long now = System.nanoTime(); 
			delta += (now - lastTime) / ns; 
			lastTime = now; 
			while(delta >= 1) {
				tick();
				delta--; 
			}
			if(running) {
				render();
				frames++; 
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000; 
//				System.out.println("FPS: " + frames);
				frames = 0; 
			}
		}
		stop(); 
	}
	
	private void tick() {
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100; 
					gameState = STATE.End; 
					handler.clearEnemys();
					}
				} 
			} else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); 
		if(bs == null) {
			this.createBufferStrategy(3);
			return; 
		}
		
		Graphics g = bs.getDrawGraphics(); 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT); 
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		} else if(gameState == STATE.Shop) {
			shop.render(g);
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			menu.render(g); 
			handler.render(g);
		} 
		g.dispose();
		bs.show(); 
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return (int) (var = max); 
		} else if(var <= min) {
			return (int) (var = min); 
		} else {
			return var; 
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game(); 
	}

}
