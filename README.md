# Flocking-simulation
Inspired from this: https://www.red3d.com/cwr/boids/
The basic flocking model enables each individual to maneuver based on the average positions and angular velocities of all flockmates with in the passive perception range of the individual with three simple steering behaviors:
  1. Separation: steer to avoid crowding local flockmates 
  2. Alignment: steer towards the average heading of local flockmates 
  3. Cohesion: steer to move toward the average position of local flockmates 
  
A feature is then added to allow each individual to avoid obstacle.

Source files to be compiled:
+ in default package
	+ GUI.java (entry point, main class)
	+ Gameplay.java 

+ in drawing package
	+ Canvas.java 

+ in flockingbehaviors package:
	+ Alignment.java
	+ Cohesion.java
	+ FlockingBehaviour.java
	+ Separation.java 

+ in geometry package
	+ CartesianCoordinate.java
	+ LineSegment.java
	+ TrigonometryException.java
	+ TrigonometrySupplement.java
	+ Vector.java

+ in tools package
	+ Utils.java

+ in obstacle package
	+ Obstacle.java

+ in turtle package
	+ FlockingTurtle.java
	+ DynamicTurtle.java
	+ RandomTurtleB.java
	+ Turtle.java

Finally, please press add button to add individual onto the screen.
