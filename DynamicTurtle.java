package turtle;

import drawing.Canvas;

public class DynamicTurtle extends Turtle {
	protected int speed = 0;
	private int distance = 0;
	
	public DynamicTurtle(Canvas myCanvas) {
		super(myCanvas);
	}
	
	public DynamicTurtle(Canvas myCanvas, double xPos, double yPos) {
		super(myCanvas);
		setStartPointX(xPos);
		setStartPointY(yPos);
		draw();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void update(int time) {
		distance = speed * time;
		move(distance);
	}
}
