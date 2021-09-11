package flockingbehaviors;
import java.util.List;
import geometry.CartesianCoordinate;
import geometry.PolarVector;
import turtle.FlockingTurtle;

public interface FlockingBehaviour {
	public abstract double behaviour(List<FlockingTurtle> turtles, CartesianCoordinate myPosition, PolarVector velocity, final int PERCEPTION_RADIUS);
	
	public abstract void setWeight(double weight);
}
