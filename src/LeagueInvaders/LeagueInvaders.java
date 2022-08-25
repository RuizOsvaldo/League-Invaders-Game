package LeagueInvaders;
import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame gameFrame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	LeagueInvaders(){
		panel = new GamePanel();
		gameFrame = new JFrame();
		
	}
	
	void setUp() {
		gameFrame.add(panel);
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.addKeyListener(panel);
	}
	
	public static void main(String[] args) {
		new LeagueInvaders().setUp();
	}
}
