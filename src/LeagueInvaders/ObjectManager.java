package LeagueInvaders;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;


public class ObjectManager implements ActionListener {
	
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void addProjectile(Projectile missile) {
		projectiles.add(missile);
	}
	
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH-30),0,50,50));
	}
	
	public void update() {
		
		//for aliens
		for(int i = 0; i< aliens.size();i++) {
			aliens.get(i).update();
			if(aliens.get(i).y>LeagueInvaders.HEIGHT ) {
				aliens.get(i).isActive = false;
			}
		}
		
		//for missiles
		for(int i = 0; i< projectiles.size();i++) {
			projectiles.get(i).update();
			if(projectiles.get(i).y>LeagueInvaders.HEIGHT ) {
				projectiles.get(i).isActive = false;
			}
		}
		
		checkCollision();
		purgeObjects();
		
	}
	
	public void draw(Graphics g) {
		
		
		//drawing aliens
		for(int i = 0; i< aliens.size();i++) {
			aliens.get(i).draw(g);			
		}
		
		//drawing bullets
		for(int i = 0; i< projectiles.size();i++) {
			projectiles.get(i).draw(g);			
		}
		rocket.draw(g);
	}
	
	public void purgeObjects() {
		
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
		
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
		System.out.println("Alien added");
	}
	
	public void checkCollision() {
		
		
				
		for(int i = 0; i < aliens.size(); i++) {
			for(int j = 0; j < projectiles.size(); j++) {
				if(aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
					rocket.isActive = false;
					aliens.get(i).isActive = false;
					System.out.println("Alien and Rocketship collided");
					
				}
				if (projectiles.get(j).collisionBox.intersects(aliens.get(i).collisionBox)) {
					projectiles.get(j).isActive = false;
					aliens.get(i).isActive = false;
					System.out.println("Alien shot");
					score++;
				}
			}
		}
	}

}
