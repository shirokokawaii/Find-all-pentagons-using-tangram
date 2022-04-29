package LIAO.entity;


public class Point {
	int angle;
	double length;

	public Point(int angle, double length) {
		this.angle = angle;
		this.length = length;
	}

	public int getAngle(){
		return angle;
	}
	public double getLength() {
		return length;
	}
	public void addLength(double length) {
		this.length += length;
	}

	@Override
	public String toString() {
		return "Point{" +
				"angle=" + angle +
				", length=" + length +
				"}\n";
	}
}

