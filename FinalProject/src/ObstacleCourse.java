import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ObstacleCourse extends JPanel implements KeyListener{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	
	private boolean jumpFlag, superJumpFlag, done = false;
	
	private Character character;
	
	private Platform platform, platform2, platform3;
	
	private MovingImage restartButton;
	
	private int superToken, jumpToken;
	
	private double score, highscore = 0.0;
	
	public ObstacleCourse() {
		super();
		setBackground(Color.GRAY);

		int randomY = 200* (int)(100*(2*Math.random() + 2));
		int randomWidth = 200 * (int)(Math.random() * 3 + 1);
		
		character = new Character("HenryClear__.png", 50 , 0);
		platform = new Platform(200,500, 1200);
		platform2 = new Platform(300, randomY, randomWidth);
		platform3 = new Platform(400, randomY, randomWidth);
		superToken = 1;
	}
	  public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	    platform.draw(g,this);
	    platform2.draw(g, this);
	    platform3.draw(g, this);
	    character.draw(g,this);

		g.setFont(new Font("Monospaced", Font.BOLD, 18));
		{	    
	    	g.setColor(Color.WHITE);
			{
				g.drawString("Score  " + (int)score, 75, 50);
				g.drawString("Highscore " + (int)highscore, 575, 50);
			}
			g.setColor(Color.YELLOW);
			g.drawString("Super Jumps! (Press Space) " + superToken, 225, 50);
		}
	    if(done == true)
    	{
	    	g.setColor(Color.WHITE);
    		g.setFont(new Font("Monospaced", Font.BOLD, 35));
    		g.drawString("GAME OVER!", 270, 220);
    		g.setColor(Color.RED);
    		g.setFont(new Font("Monospaced", Font.BOLD, 35));
    		g.drawString("Final Score: " + (int)score, 200, 300);
    		
    		restartButton = new MovingImage("RESTART.png", 245, 350, 250, 100);
    		restartButton.draw(g, this);
    		
    		if(score  > highscore)
    			highscore = score;
    	}


	  }
	  public void run() {
		while(true) {
		  	character.fall(platform, platform2, platform3);
		  		
		  	if(jumpFlag == true)
		  		character.jump();
		  	if(superJumpFlag == true)
		  		character.superJump();
		  	if(done != true)
		  		score += 0.25;
			
		  	try
		  	{
		  		Thread.sleep(20);
		  	} catch(InterruptedException e)
		  	{
		  		e.printStackTrace();
		  	}
		  		
		  	platform.moveToLeft();
		  	platform2.moveToLeft();
		  	platform3.moveToLeft();
		  		
		  	int randomY1 = (int)(100*(2*Math.random() + 3));
		  	int randomY2 = (int)(100*(2*Math.random() + 2));
		  	int randomWidth =  200 * (int)(Math.random() * 3 + 1);
		  		
		  	if(platform.getX() == -1*platform.getWidth())
		  		platform = new Platform(800, randomY1, randomWidth);
		  	if(platform2.getX() == -1*platform2.getWidth())
		  		platform2 = new Platform(800, randomY2, randomWidth);
		  	if(platform3.getX() == -1*platform3.getWidth())
	  			platform3 = new Platform(800, randomY2, randomWidth);
		  		
		  	repaint();
		  	checkcharacter();
		  	}
		}
	  
	public void keyTyped(KeyEvent e) {

	}
	  
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
	  		jumpFlag = true;
	  		
	  		if(character.getOnSurface() == true)
	  			jumpToken ++;
	  		
	  		if(jumpToken == 5){
	  			jumpToken = 0;
	  			superToken += 1;
	  		}
	  	}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE && superToken >= 1 && character.getOnSurface() == true){
			superJumpFlag = true;
			superToken --;
			repaint();
		}
		
		if(done == true && e.getKeyCode() == KeyEvent.VK_R)
		{
			int randomY = 200* (int)(100*(2*Math.random() + 2));
			int randomWidth = 200 * (int)(Math.random() * 3 + 1);
			
			character = new Character("HenryClear__.png", 50 , 0);
			platform = new Platform(200,500, 1200);
			platform2 = new Platform(300, randomY, randomWidth);
			platform3 = new Platform(400, randomY, randomWidth);
			superToken = 1;
			jumpToken = 0;
			score = 0.0;
			
			done = false;
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
	  	if (e.getKeyCode() == KeyEvent.VK_UP)
	  		jumpFlag = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			superJumpFlag = false;
	}

	  
	  public void checkcharacter() {
	  	int x = character.getX() + character.getWidth()/2;
	  	int y = character.getY() + character.getHeight()/2;
	  	if (x < 0 || x > DRAWING_WIDTH ||y > DRAWING_HEIGHT)
	  		{
	  			done = true;
	  		}
	  }

	public static void main(String[] args) {
			JFrame w = new JFrame("Obstacle Course");
		    w.setBounds(100, 100, 800, 600);
		    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    ObstacleCourse panel = new ObstacleCourse();
		    w.add(panel);
		    w.setResizable(false);
		    w.setVisible(true);
		    w.addKeyListener(panel);
		    panel.run();
	}
}

