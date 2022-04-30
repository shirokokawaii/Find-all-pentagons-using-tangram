package LIAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static LIAO.Connector.connectAll;
import static LIAO.entity.Tangram.*;
import static LIAO.entity.Tangram.S7;

public class AlgorithmTest {
	public AlgorithmTest(Shape s0, Shape s1, Shape s2, Shape s3, Shape s4, Shape s5, Shape s6, Shape s7) {
		this.s[0] = s0;
		this.s[1] = s1;
		this.s[2] = s2;
		this.s[3] = s3;
		this.s[4] = s4;
		this.s[5] = s5;
		this.s[6] = s6;//s6 is the base
		this.s[7] = s7;//s7 is another base
	}
	Shape[] s = new Shape[8];
	ArrayList<Shape> answerSet = new ArrayList<Shape>(); 

	private void displayAnswer(Shape shape) {
		ArrayList<Integer> angleSet = checkAngle(shape);
		if(shape.size() == 5) {//This shape is a pentagon, draw and display it
			answerSet.add(shape);
			System.out.println("hit:" + shape.size());
			System.out.println(angleSet);
			//here needs to draw and display the answer shape*****
		}
		else {//This shape is not a pentagon, only output its angleSet on the terminal
			System.out.print(angleSet.size() );
			System.out.println(angleSet);
		}
	}

	public ArrayList<Integer> checkAngle(Shape shape) {
		int len = shape.size();
		ArrayList<Integer> angleSet = new ArrayList<Integer>(); 
		for(int i=0;i<len;i++) {
			angleSet.add(shape.points.get(i).getAngle());
		}
		return angleSet;
	}
	
	public ArrayList<Shape> bfsSearch(int n) {
		Queue<Shape> set1 = new LinkedList<Shape>();
		set1.offer(s[6]);
		set1.offer(s[7]);
		for(int i=0;i<n;i++) {
			HashMap<ArrayList<Integer>,Integer> angleSetMap = new HashMap<>();
			System.out.println("Adding "+(i+1)+"st shape");
			Queue<Shape> set2 = new LinkedList<Shape>();
			while(!set1.isEmpty()) {
				Shape order = new Shape();
				order = set1.poll();
				for(int j=0;j<6;j++) {
					if(!order.contains(s[j])) {
						set2 = connectAll(order,s[j]);
					}
				}
			}
			while(!set2.isEmpty()){
				Shape shape = set2.poll();
				if(shape == null){
					continue;
				}
				ArrayList<Integer> angleSetTem = getAngleList(shape);
				if(!angleSetMap.containsKey(angleSetTem)){
					angleSetMap.put(angleSetTem, 1);
					set1.offer(shape);
				}
			}
		}
		while(!set1.isEmpty()) {
			displayAnswer(set1.poll());
		}
		return answerSet;
	}
	

	private ArrayList<Integer> getAngleList(Shape shape){
		ArrayList<Integer> angleSet = new ArrayList<Integer>();
		int len = shape.size();
		int min = 8;
		int index = 0;
		for(int i=0;i<len;i++){
			int tem = shape.points.get(i).getAngle();
			if(tem <= min){
				min = tem;
				index = i;
			}
		}
		for(int i=0;i<len;i++){
			if(index > len-1){
				index = 0;
			}
			angleSet.add(shape.points.get(index).getAngle());
			index++;
		}
		return angleSet;
	}

	public static void main(String[] args) {
		AlgorithmTest algorithm = new AlgorithmTest(S0, S1, S2, S3, S4, S5, S6, S7);

		algorithm.bfsSearch(1);



	}

}

