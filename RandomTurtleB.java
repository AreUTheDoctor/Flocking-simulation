package turtle;
import drawing.Canvas;

public class RandomTurtleB extends DynamicTurtle {
	private int counter; 
	protected double angle = 0;
	private int distance = 0;
	
	public RandomTurtleB(Canvas myCanvas) {
		super(myCanvas);
	}
	
	public RandomTurtleB(Canvas myCanvas, double xPos, double yPos) {
		super(myCanvas, xPos, yPos);
		counter = (int)(Math.random()*(5 - 1) + 1);
	}
	
	@Override
	public void update(int time) {
		distance = time * speed;
		turn(angle);
		move(distance);
	}
	
	public double randomDirection() {
		counter--;
		if (counter == 0) {
			angle = (double)(Math.random()*(50 - (-50)) + (-50));
			counter = (int)(Math.random()*(5 - 1) + 1);
		}
		return angle;
	}
}
