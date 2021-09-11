package turtle;

import java.util.List;
import drawing.Canvas;
import flockingbehaviors.Alignment;
import flockingbehaviors.Cohesion;
import flockingbehaviors.FlockingBehaviour;
import flockingbehaviors.Separation;
import geometry.CartesianCoordinate;
import obstacle.Obstacle;

public class FlockingTurtle extends RandomTurtleB {
	private List<FlockingTurtle> turtles;
	private double alignWeight;
	private double cohesionWeight;
	private double separateWeight;
	private final int PERCEPTION_RADIUS = 50;
	private double steerAngle;
	
	public FlockingTurtle(Canvas myCanvas) {
		super(myCanvas);
		// TODO Auto-generated constructor stub
	}

	public FlockingTurtle(Canvas myCanvas, double xPos, double yPos, List<FlockingTurtle> turtles) {
		super(myCanvas, xPos, yPos);
		// TODO Auto-generated constructor stub
		this.turtles = turtles;
	}
	
	public void setAlign(double align) {
		alignWeight = align;
	}
	
	public void setCohesion(double cohesion) {
		cohesionWeight = cohesion;
	}
	
	public void setSeparate(double separate) {
		separateWeight = separate;
	}
	
	public double applyBehaviours() {
		steerAngle = 0;
		
		FlockingBehaviour alignment = new Alignment();
		alignment = new Alignment();
		alignment.setWeight(alignWeight);
		steerAngle += alignment.behaviour(turtles, cc1, velocity, PERCEPTION_RADIUS);
		
		FlockingBehaviour cohesion = new Cohesion();
		cohesion = new Cohesion();
		cohesion.setWeight(cohesionWeight);
		steerAngle += cohesion.behaviour(turtles, cc1, velocity, PERCEPTION_RADIUS);

		FlockingBehaviour separation = new Separation();
		separation = new Separation();
		separation.setWeight(separateWeight);
		steerAngle += separation.behaviour(turtles, cc1, velocity, PERCEPTION_RADIUS);
		
		return steerAngle;
	}
	
	public double avoidObstacle(int time, double steer, Obstacle obstacle) {
		int distance = speed * time;
		double angle = 0;
		if (cc1.getX() > obstacle.getxLowerBoundary() 
		   && cc1.getX() < obstacle.getxUpperBoundary()
		   && cc1.getY() > obstacle.getyLowerBoundary()
		   && cc1.getY() < obstacle.getyUpperBoundary()) {
			angle += 5;
			turn(angle);
			distance = speed * time;
			CartesianCoordinate probe = probeAhead(distance);
			
			while(probe.getX() > obstacle.getxLowerBoundary() 
			   && probe.getX() < obstacle.getxUpperBoundary()
			   && probe.getY() > obstacle.getyLowerBoundary()
			   && probe.getY() < obstacle.getyUpperBoundary()) {
				angle += 5;
				turn(angle);
				distance++;
				probe = probeAhead(distance);
			}
			
		} else {
			CartesianCoordinate probe = probeAhead(distance);
			
			while(probe.getX() > obstacle.getxLowerBoundary() 
			   && probe.getX() < obstacle.getxUpperBoundary()
			   && probe.getY() > obstacle.getyLowerBoundary()
			   && probe.getY() < obstacle.getyUpperBoundary()) {
				angle += 5;
				turn(angle);
				distance++;
				probe = probeAhead(distance);
			}
		}
		return steer;
	}
	
	public void updateSteer(int time, double steer) {
		int distance = speed * time;
		turn(steer);
		move(distance);
	}
	
	public double getSteer() {
		return steerAngle;
	}
}
