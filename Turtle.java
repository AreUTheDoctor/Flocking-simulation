package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.TrigonometryException;
import geometry.TrigonometrySupplement;
import geometry.PolarVector;

public class Turtle {
	private Canvas myCanvas;
	private boolean draw;
	protected CartesianCoordinate cc1;
	private CartesianCoordinate cc2;
	private final int TURTLE_SIZE = 10;
	private final int TURTLE_ANGLE = 240;
	private double sin;
	private double cos;
	private double angle;
	protected PolarVector velocity;

	public Turtle(Canvas myCanvas) {
		this.myCanvas = myCanvas;
		draw = false;
		
		//Set default starting point at centre
		cc1 = new CartesianCoordinate(0.0, 0.0);
		cc2 = new CartesianCoordinate(0.0, 0.0);
		
		// reference point for trigonometry circle
		sin = 0.0;
		cos = 1.0; 
		angle = 0.0;
	}

	public void move(double length) {
		double x2 = cc1.getX() + length * cos;
		double y2 = cc1.getY() + length * sin;
		cc2.setX(x2);
		cc2.setY(y2);
		
		if (draw == true) {
			myCanvas.drawLineBetweenPoints(cc1, cc2);
		}
		
		storeVelocity();
		
		cc1 = new CartesianCoordinate(cc2.getX(), cc2.getY());	
		cc2 = new CartesianCoordinate(0.0, 0.0);
	}
	
	public CartesianCoordinate probeAhead(double length) {
		double x2 = cc1.getX() + length * cos;
		double y2 = cc1.getY() + length * sin;
		CartesianCoordinate virtualCC2 = new CartesianCoordinate(x2, y2);
		return virtualCC2;
	}
	
	private void storeVelocity() {
		velocity = new PolarVector(cc2.getX() - cc1.getX(), cc2.getY() - cc1.getY());
	}
	
	public PolarVector getVelocity() {
		return velocity;
	}
	
	public void turn(double ClockWiseAngle) {
		angle = angle + ClockWiseAngle;
		TrigonometrySupplement tool = new TrigonometrySupplement(angle);
		angle = tool.normaliseAngle();
		try {
			sin = tool.getSinWithAngle();
		} catch (TrigonometryException e) {
			System.out.println("Error sin");
		}
		try {
			cos = tool.getCosWithAngle();
		} catch (TrigonometryException e) {
			System.out.println("Error cos");
		}
	}

	public void putPenUp() {
		draw = false;
	}

	public void putPenDown() {
		draw = true;
	}
	
	//draw an equilateral triangle
	public void draw() {
		putPenDown();
		turn(TURTLE_ANGLE);
		move(TURTLE_SIZE);
		turn(TURTLE_ANGLE);
		move(TURTLE_SIZE);
		turn(TURTLE_ANGLE);
		move(TURTLE_SIZE);
		putPenUp();
	}
	
	public void undraw() {
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
		myCanvas.removeMostRecentLine();
	}
	
	public CartesianCoordinate getStartPoint() {
		return cc1;
	}

	public void setStartPointX(double x) {
		cc1.setX(x);
	}
	
	public void setStartPointY(double y) {
		cc1.setY(y);
	}
	
	public double getTurtleSize() {
		return TURTLE_SIZE;
	}

	public void wrapPosition(double xPosMax, double yPosMax) {
		if (getStartPoint().getX() > xPosMax)
			setStartPointX(0);
		else if (getStartPoint().getX() < 0) {
			setStartPointX(xPosMax);
		}
		
		if (getStartPoint().getY() > yPosMax)
			setStartPointY(0);
		else if (getStartPoint().getY() < 0) {
			setStartPointY(yPosMax);
		}
	}
	
	public Canvas getMyCanvas() {
		return myCanvas;
	}
}
