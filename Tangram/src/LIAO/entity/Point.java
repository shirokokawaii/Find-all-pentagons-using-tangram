package LIAO.entity;


import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

public class Point {
	@JSONField(name = "ANGLE")
	int angle;
	@JSONField(name = "LENGTH")
	double length;

	@JSONField(serialize = false)
	boolean flag;

	@JSONField(serialize = false)
	char name;

	@JSONCreator
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


	@JSONField(name = "ANGLE")
	public int getAngle(){
		return angle;
	}
	@JSONField(name = "ANGLE")
	public void setAngle(int angle) {this.angle = angle;}
	@JSONField(name = "LENGTH")
	public double getLength() {
		return length;
	}
	@JSONField(name = "LENGTH")
	public void setLength(double length) {this.length = length;}

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

