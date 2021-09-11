package geometry;

public class PolarVector {
	private double x;
	private double y;
	private double angle;
	
	public PolarVector(double xComponent, double yComponent) {
		this.x = xComponent;
		this.y = yComponent;
		setAngle();
	}
	
	private void setAngle() {
		double radian = Math.atan2(y, x);
		angle = Math.toDegrees(radian);
	}
	
	public double getX() {
		return x;		
	}
	
	public double getY() {
		return y;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public PolarVector normalise() {
		PolarVector unitVector = this;
		CartesianCoordinate root = new CartesianCoordinate(0, 0);
		CartesianCoordinate heading = new CartesianCoordinate(x, y);
		LineSegment line = new LineSegment(root, heading);
		double vectorLength = line.length();
		unitVector.divideScalar(vectorLength);
		return unitVector;
	}
	
	public void addVectors(PolarVector vector2) {
		double xComponent2 = vector2.getX();
		double yComponent2 = vector2.getY();
		x += xComponent2;
		y += yComponent2;
		setAngle();
	}
	
	public void substractVectors(PolarVector vector2) {
		double xComponent2 = vector2.getX();
		double yComponent2 = vector2.getY();
		x -= xComponent2;
		y -= yComponent2;
		setAngle();
	}
	
	public void divideScalar(double coefficient) {
		try {
			x = x / coefficient;
			y = y / coefficient;
		} catch (ArithmeticException e){
			System.out.println("Divide by 0");
		}
		setAngle();
	}
	
	public void multiplyScalar(double coefficient) {
		x *= coefficient;
		y *= coefficient;
		setAngle();
	}
}
