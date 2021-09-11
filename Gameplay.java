import java.util.List;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;
import obstacle.Obstacle;
import tools.Utils;
import turtle.FlockingTurtle;
import turtle.RandomTurtleB;

public class Gameplay {
	private List<FlockingTurtle> turtles;
	private double alignWeight;
	private double cohesionWeight;
	private double separationWeight;
	
	private Obstacle obstacle;
	private Canvas canvas;

	protected final int PERCEPTION_RADIUS = 50;
	
	public Gameplay(List<FlockingTurtle> turtles, Canvas canvas, Obstacle obstacle) {
		this.canvas = canvas;
		this.turtles = turtles;
		this.obstacle = obstacle;
	}

	private void collisionDetection(CartesianCoordinate myPosition) {
		synchronized (turtles) {
			for (FlockingTurtle neighbor : turtles) {
				CartesianCoordinate neighborPosition = new CartesianCoordinate(neighbor.getStartPoint().getX(), neighbor.getStartPoint().getY());
				LineSegment distance = new LineSegment(myPosition, neighborPosition);
				if (distance.length() > 0 && distance.length() < neighbor.getTurtleSize()) {
					System.out.println("Collision at: (" +myPosition.getX() +", " +myPosition.getY() +")" +"\n");
				}
			}
		}
	}
	
	public void setAlign(double align) {
		alignWeight = align;
	}
	
	public void setCohesion(double cohesion) {
		cohesionWeight = cohesion;
	}
	
	public void setSeparate(double separate) {
		separationWeight = separate;
	}
	
	public void gameLoop() {
		int deltaTime = 1;
		boolean continueRunning = true;

		while (continueRunning) {
			synchronized (turtles) {
				for (FlockingTurtle turtle : turtles) {
					turtle.undraw();
				}
			}
			
			synchronized (turtles) {
				for (FlockingTurtle turtle : turtles) {
					turtle.setAlign(alignWeight);
					turtle.setCohesion(cohesionWeight);
					turtle.setSeparate(separationWeight);
					double steerAngle = turtle.applyBehaviours();
					
					if (steerAngle == 0) {
						RandomTurtleB tempTurtle = new RandomTurtleB(canvas);
						tempTurtle = (RandomTurtleB) turtle;
						steerAngle = ((RandomTurtleB) tempTurtle).randomDirection();
						turtle.avoidObstacle(deltaTime, steerAngle, obstacle);
						turtle.updateSteer(deltaTime, steerAngle);
					} else {
						steerAngle = turtle.avoidObstacle(deltaTime, steerAngle, obstacle);
						turtle.updateSteer(deltaTime, steerAngle);
					}
					collisionDetection(turtle.getStartPoint());
					turtle.wrapPosition(canvas.getWidth(), canvas.getHeight());
				}
			}

			synchronized (turtles) {
				for (FlockingTurtle turtle : turtles) {
					turtle.draw();
				}
			}
			Utils.pause(deltaTime*20);
		}
	}
}
