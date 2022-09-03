package LeagueInvaders;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    Timer frameDraw;
    Timer alienSpawn;
    
    Font titleFont;
    Font bodyFont;
    Rocketship rocket = new Rocketship(250,700, 50, 50);
    ObjectManager manager = new ObjectManager(rocket);
    
    int currentState = MENU;
    
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	

	
    GamePanel(){
        frameDraw = new Timer(1000/60, this);
        frameDraw.start();
        
        
        titleFont = new Font("Arial", Font.BOLD, 48);
        bodyFont = new Font("Arial", Font.PLAIN, 24);
       
        if (needImage) {
            loadImage ("space.png");
        }
    }
    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	public void startGame() {
		alienSpawn = new Timer(1000, manager);
	    alienSpawn.start();
	}
	
	//Updating each state
	public void updateMenuState(){
		
	}
	public void updateGameState(){  
		manager.update();
		if(rocket.isActive == false) {
			currentState = END;
		}
	}
	public void updateEndState(){  
		
	}
	
	//Drawing each game state
	public void drawMenuState(Graphics g){  
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		//Setting font for MENU State
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("LEAGUE INVADERS", 12, 75);
		g.setFont(bodyFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to Start.", 120, 400);
		g.drawString("Press SPACE for instructions.", 90, 600);
		
	}
	
	public void drawGameState(Graphics g){  
		
		
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		manager.draw(g);
		
	}
	
	public void drawEndState(Graphics g){  
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		//Setting font for END Screen
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 100, 75);
		g.setFont(bodyFont);
		g.setColor(Color.WHITE);
		g.drawString("You Killed "+manager.getScore()+" Enemies", 120, 400);
		g.drawString("Press ENTER to Restart.", 90, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		    System.out.println("Action");
		    repaint();
		}else if(currentState == GAME){
		    updateGameState();
		    System.out.println("Action");
		    repaint();
		}else if(currentState == END){
		    updateEndState();
		    System.out.println("Action");
		    repaint();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		//Moving to GAME State
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	alienSpawn.stop();
		        currentState = MENU;
		        rocket = new Rocketship(250,700, 50, 50);
		        manager = new ObjectManager(rocket);
		    } else {
		        currentState++;
		        if (currentState == GAME) {
		        	startGame();
		        }
		    }
		}
		
		
		//Checking to see which button is pressed in GAME
		if(currentState == GAME) {
			
			if(rocket.x <= LeagueInvaders.WIDTH -65 && rocket.x > 0 && rocket.y <= LeagueInvaders.HEIGHT -90 && rocket.y >0 ) {
				if (e.getKeyCode()==KeyEvent.VK_UP) {
				    System.out.println("UP");
				    rocket.up();
				} else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				    System.out.println("DOWN");
				    rocket.down();
				} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				    System.out.println("LEFT");
				    rocket.left();
				} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				    System.out.println("RIGHT");
				    rocket.right();
				} else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					manager.addProjectile(rocket.getProjectile());
					System.out.println("bullet shot");
				}
				
				
			} else if(rocket.x > LeagueInvaders.WIDTH -65) {
				rocket.x = LeagueInvaders.WIDTH -65;
			} else if(rocket.x <= 0) {
				rocket.x = 3;
			} else if(rocket.y > LeagueInvaders.HEIGHT -90) {
				rocket.y = LeagueInvaders.HEIGHT - 90;
			} else if(rocket.y <= 0) {
				rocket.y = 3;
			}
		} 
		
		
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
	       
	}
}
