package geometry;

import java.text.DecimalFormat;

public class CartesianCoordinate {
	private double xCoord;
	private double yCoord;
	
	public CartesianCoordinate(double x, double y) {
		xCoord = x;
		yCoord = y;
	}
	
	public double getX(){
		DecimalFormat df = new DecimalFormat("#.##");
		xCoord = Double.valueOf(df.format(xCoord));
		return xCoord;
	}
	
	public double getY() {
		DecimalFormat df = new DecimalFormat("#.##");
		yCoord = Double.valueOf(df.format(yCoord));
		return yCoord;
	}
	
	public void setX(double x) {
		xCoord = x;
	}
	
	public void setY(double y) {
		yCoord = y;
	}
}

