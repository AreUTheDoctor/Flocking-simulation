package flockingbehaviors;
import java.util.List;

import geometry.CartesianCoordinate;
import geometry.LineSegment;
import geometry.PolarVector;
import turtle.FlockingTurtle;

public class Cohesion implements FlockingBehaviour {
	
	private double weight;

	public Cohesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double behaviour(List<FlockingTurtle> turtles, CartesianCoordinate myPosition, 
			                PolarVector velocity, final int PERCEPTION_RADIUS) {
		double xAveragePosition = 0;
		double yAveragePosition = 0;
		double neighborCounter = 0;
		PolarVector desired = new PolarVector(0, 0);
		double steer = 0;
		
		synchronized (turtles) {
			for (FlockingTurtle neighbor : turtles) {
				CartesianCoordinate neighborPosition = neighbor.getStartPoint();
				LineSegment distance = new LineSegment(myPosition, neighborPosition);
				if (distance.length() > 0 && distance.length() < PERCEPTION_RADIUS) {
					xAveragePosition += neighborPosition.getX();
					yAveragePosition += neighborPosition.getY();
					neighborCounter++;
				}
			}
		}
		
		if (neighborCounter != 0) {
			xAveragePosition /= neighborCounter;
			yAveragePosition /= neighborCounter;
			double xDiff = xAveragePosition - myPosition.getX();
			double yDiff = yAveragePosition - myPosition.getY();
			desired = new PolarVector(xDiff, yDiff);
			
			if (desired.getX() != 0 && desired.getY() != 0) {
				double desiredAngle = desired.getAngle();
				double velocityAngle = velocity.getAngle();
				steer = (desiredAngle - velocityAngle) * weight;
			}
		}
		return steer;
	}

	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
