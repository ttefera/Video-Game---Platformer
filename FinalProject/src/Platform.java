public class Platform extends MovingImage{
		
		private static int RANGE = 400;
		
		private int xcoord, ycoord;
		private int range;
		private int moveDist;
		int dir;
		
		public Platform(int x, int y, int w) {
			super("platform.png",x,y,w, 50);
			// platform = new MovingImage("bricks.png",200,500,400,50);
			xcoord = x;
			ycoord = y;
			range = RANGE;
			dir = -1;
			}
		
		
		public void moveToLeft()
		{
			moveByAmount(dir*10,0);	
		}

		

}
