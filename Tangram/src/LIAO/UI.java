package LIAO;

import java.awt.*;
import java.util.LinkedList;
import static LIAO.entity.Tangram.*;
import javax.swing.*;

public class UI {
	JLabel plabel, title, group, kind, alg, load;
	JPanel panel;
	JComboBox<String> type, method;
	JTextField path;
	JButton button1, button2, button3, button4;
	LinkedList<LinkedList<Shape>> answerList = new LinkedList<>();
	int way = 0;
	JPanel[] p = new JPanel[100];
	int left, top;
	int x, y, k;
	int n = 1, shapek = 0, t = 100;

	public void CreateUI() {
		JFrame rf = new JFrame("Tangram Solver");
		rf.setBounds(100, 100, 1400, 1080);
		rf.setLocationRelativeTo(null);
		rf.getContentPane().setLayout(null);
		rf.setBackground(Color.white);
		rf.setLayout(null);
		rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel title = new JLabel();
		title.setBounds(10, 1, 500, 85);
		title.setForeground(Color.black);
		title.setFont(new Font("TimesRoman", 1, 40));
		title.setText("SE360 Tangram Solver");

		JLabel group = new JLabel();
		group.setBounds(10, 58, 700, 85);
		group.setForeground(Color.black);
		group.setFont(new Font("TimesRoman", 1, 15));
		group.setText("Group 12: Liao Weiyu, Yao Tianyi, Yu Mengke, Fu Zhanchao");

		panel = new JPanel();
		panel.setBounds(511, 10, 750, 750);
		panel.setBackground(Color.white);
		panel.setVisible(true);
		rf.add(panel);
		
		JLabel now = new JLabel(String.valueOf(n));
		now.setBounds(860, 769, 50, 20);
		now.setFont(new Font("DejaVu Sans Mono", 1, 20));
		JLabel l = new JLabel("/");
		l.setBounds(910, 769, 20, 20);
		l.setFont(new Font("DejaVu Sans Mono", 1, 20));
		JLabel total = new JLabel("???");
		total.setBounds(920, 769, 50, 20);
		total.setFont(new Font("DejaVu Sans Mono", 1, 20));

		JLabel kind = new JLabel("Pentagonal Type：");
		kind.setBounds(15, 162, 200, 25);
		kind.setFont(new Font("DejaVu Sns Mono", 1, 20));
		JComboBox<String> type = new JComboBox<String>();
		type.setBounds(15, 195, 135, 30);
		type.addItem("   All pentagon");
		type.addItem("   Convex pentagon"); // 凸
		type.addItem("   Concave pentagon");
		type.addActionListener((e) -> {
			shapek = type.getSelectedIndex();
		});
		JLabel alg = new JLabel("Search Method：");
		alg.setBounds(15, 265, 200, 20);
		alg.setFont(new Font("DejaVu Sans Mono", 1, 20));
		JComboBox<String> method = new JComboBox<String>();
		method.setBounds(15, 295, 135, 30);
		method.addItem("   --Please choose--");
		method.addItem("              DFS");
		method.addItem("              BFS");
		method.addItem("                A*");
		method.addItem("              ida*");
		// 监听
		method.addActionListener((e) -> {
			int index = method.getSelectedIndex();
			if (index == 1) {
				way = 1;
			}
			if (index == 2) {
				way = 2;
			}
			if (index == 3) {
				way = 3;
			}
			if (index == 4) {
				way = 4;
			}
		});
		
		JLabel titleNow = new JLabel();
		titleNow.setBounds(10, 600, 700, 85);
		titleNow.setForeground(Color.black);
		titleNow.setFont(new Font("TimesRoman", 1, 15));
		titleNow.setText("");
		
		button1 = new JButton();
		button1.setBounds(15, 700, 135, 35);
		button1.setText("Different ways");
		button1.addActionListener((e) -> {
			if(answerList.get(n).size() !=1) {
				Pen pen = new Pen(rf, panel);
				pen.beforeDraw(answerList.get(n).get(k%answerList.get(n).size()), 260, 350, 60);
				pen.draw();
				k++;
			}

		});

		button2 = new JButton();
		button2.setBounds(15, 750, 135, 35);
		button2.setText("Solve");
		button2.addActionListener((e) -> {
			Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
			algorithm.displayWhileCalculating(rf, panel, 260, 350, 60);
			algorithm.showLabelWhileCalculating(titleNow);
			answerList.clear();
			if (way == 0) {
				total.setText("Please choose a solve approach before start");
			}
			if (way == 1) {
				algorithm.dfsSearch();
			}
			if (way == 2) {
				algorithm.bfsSearch();
			}
			if (way == 3) {
				algorithm.aStarSearch();
			}
			if (way == 4) {
				algorithm.aStarSearch();// ***************************************
			}
			answerList = algorithm.getAnswerList();
			t = answerList.size();
			total.setText(String.valueOf(t));
		});

		button4 = new JButton();
		button4.setBounds(980, 765, 75, 25);
		button4.setText("Next");
		button4.addActionListener((e) -> {
			Shape shape;
				if(shapek == 1) {
					int index=0;
					n--;
					while(index<5) {
						n++;
						shape = answerList.get(n%answerList.size()).get(0);
						index=0;
						for(int i=0;i<shape.size();i++) {
							if(shape.getAngel(i)<4) {
								index++;
							}
						}
					}
				}
				if(shapek == 2) {
					int index=5;
					n--;
					while(index==5) {
						n++;
						shape = answerList.get(n%answerList.size()).get(0);
						index=0;
						for(int i=0;i<shape.size();i++) {
							if(shape.getAngel(i)<4) {
								index++;
							}
						}
					}
				}
					Pen pen = new Pen(rf, panel);
					pen.beforeDraw(answerList.get(n%answerList.size()).get(0), 260, 350, 60);
					k=0;
					pen.draw();
					n++;
					now.setText(String.valueOf(n%answerList.size()+"-"+answerList.get(n%answerList.size()).size()));
					});

		rf.getContentPane().add(title, null);
		rf.getContentPane().add(group, null);
		rf.getContentPane().add(panel, null);
		rf.getContentPane().add(kind, null);
		rf.getContentPane().add(type, null);
		rf.getContentPane().add(alg, null);
		rf.getContentPane().add(method, null);
		rf.getContentPane().add(button1, null);
		rf.getContentPane().add(button2, null);
		// rf.getContentPane().add(button3,null);
		rf.getContentPane().add(button4, null);
		rf.getContentPane().add(now, null);
		rf.getContentPane().add(total, null);
		rf.getContentPane().add(l, null);
		rf.getContentPane().add(titleNow, null);
		rf.setVisible(true);
	}

	public static void main(String[] args) {
		new UI().CreateUI();
	}
}
