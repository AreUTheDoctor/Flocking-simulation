package flockingbehaviors;

import java.util.List;
import geometry.CartesianCoordinate;
import geometry.LineSegment;
import geometry.PolarVector;
import turtle.FlockingTurtle;

public class Alignment implements FlockingBehaviour {
	
	private double weight;

	public Alignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double behaviour(List<FlockingTurtle> turtles, CartesianCoordinate myPosition,
			                PolarVector velocity, final int PERCEPTION_RADIUS) {
		// TODO Auto-generated method stub
		PolarVector desired = new PolarVector(0, 0);
		double steer = 0;
		double neighborCounter = 0;
		
		synchronized (turtles) {
			for (FlockingTurtle neighbor : turtles) {
				CartesianCoordinate neighborPosition = neighbor.getStartPoint();
				LineSegment distance = new LineSegment(myPosition, neighborPosition);
				if (distance.length() > 0 && distance.length() < PERCEPTION_RADIUS) {
					desired.addVectors(neighbor.getVelocity()); 
					neighborCounter++;
				}
			}
		}
		
		if (neighborCounter != 0) {
			desired.divideScalar(neighborCounter);
			
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
		// TODO Auto-generated method stub
		this.weight = weight;
	}
}
