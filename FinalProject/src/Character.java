public class Character extends MovingImage {

	// FIELDS
	private double velY;
	private int jumpHeight;
	private int superJumpHeight;
	private boolean onSurface;

	
	// CONSTRUCTOR
	public Character (String line, int x, int y) {
		super(line,x,y,100,200);
		jumpHeight = 14;
		superJumpHeight = 25;
		velY = 0;

		
	}
	
	
	public void jump() {
		// JUMP!
		if(onSurface == true)
		{
			velY = -jumpHeight;
			moveByAmount(0, (int)velY);
		}
		
	}
	public void superJump(){
		if(onSurface == true)
		{
			velY = -superJumpHeight;
			moveByAmount(0, (int)velY);
		}
	}
	
	public void fall(MovingImage platform, MovingImage platform2, MovingImage platform3) {
		// FALL!
		onSurface = false;
		//velY = 10;
		if(velY < jumpHeight)
		{
			velY = velY + 0.5;
		}
		
		if(platform.isPointInImage(getX(), getY() + getHeight()) || platform2.isPointInImage(getX(), getY() + getHeight()) || platform3.isPointInImage(getX(), getY() + getHeight()))
		{
			onSurface = true;
			velY = 0;
		}
		moveByAmount(0, (int)velY);
	}
	public boolean getOnSurface()
	{
		return onSurface;
	}

	
	
}