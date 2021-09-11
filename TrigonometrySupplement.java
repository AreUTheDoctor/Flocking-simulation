package geometry;

import java.text.DecimalFormat;

public class TrigonometrySupplement {
	private double angle;
	
	public TrigonometrySupplement(double angle) {
		this.angle = angle;
	}
	
	public double normaliseAngle() {
		while (angle >= 360) {
			angle = angle - 360;
		} 
		while (angle < 0) {
			angle = angle + 360;
		}
		return angle;
	}
	
	public double getSinWithAngle() throws TrigonometryException {
		double radian = Math.toRadians(angle);
		DecimalFormat df = new DecimalFormat("#.##");
		radian = Double.valueOf(df.format(radian));
		double sin = Math.sin(radian);
		if (sin >= -1 & sin <= 1) {
			sin = Double.valueOf(df.format(sin));
		} else {
			throw new TrigonometryException();
		}
		return sin;
	}
	
	public double getCosWithAngle() throws TrigonometryException {
		double radian = Math.toRadians(angle);
		DecimalFormat df = new DecimalFormat("#.##");
		radian = Double.valueOf(df.format(radian));
		double cos = Math.cos(radian);
		if (cos >= -1 & cos <= 1) {
			cos = Double.valueOf(df.format(cos));
		} else {
			throw new TrigonometryException();
		}
		return cos;
	}
}
