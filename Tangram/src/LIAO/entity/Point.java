package LIAO.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class Point {
	@JSONField(name = "ANGLE")
	int angle;
	@JSONField(name = "LENGTH")
	double length;

	boolean flag;

	char name;

	public Point(int angle, double length) {
		this.angle = angle;
		this.length = length;
		this.flag = false;
	}



	public Point(int angle, double length, boolean flag) {
		this.angle = angle;
		this.length = length;
		this.flag = flag;
	}

	public Point(Point point) {
		this.angle = point.angle;
		this.length = point.length;
	}

	public Point(Point point, boolean flag) {
		this.angle = point.angle;
		this.length = point.length;
		this.flag = flag;
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

	public void setFlag(){
		flag = true;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}
}

