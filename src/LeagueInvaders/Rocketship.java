package LeagueInvaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.awt.Color;
public class Rocketship extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	Rocketship(int x, int y, int width, int height){
		super(x, y, width, height);
		speed = 10;
		
		if (needImage) {
		    loadImage ("rocket.png");
		}
		
	
	}
	
	void draw(Graphics g) {
        
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
        
	}
	
	void up() {
		y-=speed;
	}
	
	void down() {
		y+=speed;
	}

	void right() {
		x+=speed;
	}
	
	void left() {
		x-=speed;
	}
	
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
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
	
}
