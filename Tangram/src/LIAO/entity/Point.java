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
	char label;

	@JSONField(serialize = false)
	char name;

	@JSONCreator
	public Point(int angle, double length) {
		this.angle = angle;
		this.length = length;
		this.flag = false;
	}


	public Point(int angle, double length, char name) {
		this.angle = angle;
		this.length = length;
		this.name = name;
		this.flag = false;
	}

	public Point(int angle, double length, boolean flag) {
		this.angle = angle;
		this.length = length;
		this.flag = flag;
	}

	public Point(int angle, double length, boolean flag, char name) {
		this.angle = angle;
		this.length = length;
		this.flag = flag;
		this.name = name;
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

	public Point(Point point, boolean flag, char name) {
		this.angle = point.angle;
		this.length = point.length;
		this.flag = flag;
		this.name = name;
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
	public boolean getFlag(){
		return flag;
	}


	public char getLabel() {
		return label;
	}

	public void setLabel(char label) {
		this.label = label;
	}

	public void setName(char name) {
		this.name = name;
	}

	public char getName() {return name;}

}

