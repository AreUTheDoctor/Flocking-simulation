package flockingbehaviors;
import java.util.List;
import geometry.CartesianCoordinate;
import geometry.LineSegment;
import geometry.PolarVector;
import turtle.FlockingTurtle;

public class Separation implements FlockingBehaviour {
	
	private double weight;

	public Separation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double behaviour(List<FlockingTurtle> turtles, CartesianCoordinate myPosition, 
			                PolarVector velocity, final int PERCEPTION_RADIUS) {
		double neighborCounter = 0;
		PolarVector desired = new PolarVector(0, 0);
		double steer = 0;
		
		synchronized (turtles) {
			for (FlockingTurtle neighbor : turtles) {
				CartesianCoordinate neighborPosition = neighbor.getStartPoint();
				LineSegment distance = new LineSegment(neighborPosition, myPosition);
				if (distance.length() > 0 && distance.length() < PERCEPTION_RADIUS) {
					double xDiffGetAway = myPosition.getX() - neighborPosition.getX();
					double yDiffGetAway = myPosition.getY() - neighborPosition.getY();
					PolarVector getAway = new PolarVector(xDiffGetAway, yDiffGetAway);
					PolarVector unitVector = getAway.normalise();
					unitVector.divideScalar(distance.length());
					desired.addVectors(unitVector);
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
		this.weight = weight;
	}

}
