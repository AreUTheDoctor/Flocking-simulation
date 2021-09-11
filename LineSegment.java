package geometry;

public class LineSegment {
	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	
	public LineSegment(CartesianCoordinate startCoord, CartesianCoordinate endCoord) {
		this.startPoint = startCoord;
		this.endPoint = endCoord;
	}
	
	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}
	
	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}
	
	public double length() {
		double length;
		double squared_length = Math.pow(endPoint.getX()-startPoint.getX(), 2)
								+ Math.pow(endPoint.getY() - startPoint.getY(), 2);
		length = Math.sqrt(squared_length);
		return length;
	}
	
	public PolarVector convertIntoVector() {
		double xDiff = endPoint.getX() - startPoint.getX();
		double yDiff = endPoint.getY() - startPoint.getY();
		PolarVector vector = new PolarVector(xDiff, yDiff);
		return vector;
	}
}
