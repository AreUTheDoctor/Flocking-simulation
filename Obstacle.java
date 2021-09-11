package obstacle;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import turtle.Turtle;

public class Obstacle {
	private double width;
	private double height;
	private CartesianCoordinate topLeftCorner;
	private double xLowerBoundary;
	private double xUpperBoundary;
	private double yLowerBoundary;
	private double yUpperBoundary;
	private int outerLayer = 30;
	
	private Canvas canvas;
	
	public Obstacle(CartesianCoordinate startPoint, double width, double height, Canvas canvas) {
		topLeftCorner = startPoint;
		xLowerBoundary = startPoint.getX() - outerLayer;
		yLowerBoundary = startPoint.getY() - outerLayer;
		
		this.width = width;
		xUpperBoundary = (xLowerBoundary + outerLayer + width) + outerLayer;
		
		this.height = height;
		yUpperBoundary = (yLowerBoundary + outerLayer + height) + outerLayer;
		this.canvas = canvas;
	}
	
	public void draw() {
		Turtle turtle = new Turtle(canvas); // Passing canvas to subclass turtle
		turtle.setStartPointX(topLeftCorner.getX());
		turtle.setStartPointY(topLeftCorner.getY());
		turtle.putPenDown();
		turtle.move(width);
		turtle.turn(90);
		turtle.move(height);
		turtle.turn(90);
		turtle.move(width);
		turtle.turn(90);
		turtle.move(height);
		turtle.turn(90);
		turtle.move(width);
		turtle.putPenUp();
		
		canvas = turtle.getMyCanvas();
	}
	
	public double getxLowerBoundary() {
		return xLowerBoundary;
	}
	
	public double getxUpperBoundary() {
		return xUpperBoundary;
	}
	
	public double getyLowerBoundary() {
		return yLowerBoundary;
	}
	
	public double getyUpperBoundary() {
		return yUpperBoundary;
	}
	
	public Canvas getMyCanvas() {
		return canvas;
	}

}
