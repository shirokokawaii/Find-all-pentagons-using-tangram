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
	Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
	LinkedList<LinkedList<Shape>> answerList = new  LinkedList<>();
	JPanel[] p = new JPanel[100];
	int left,top;
	int x,y;
	int n = 1, shapek=0, t=100;
	
	public void CreateUI() {
		JFrame rf=new JFrame("Tangram Solver");
		rf.setBounds(100,100,1400,1080);
		rf.setLocationRelativeTo(null);
		rf.getContentPane().setLayout(null);
		rf.setBackground(Color.white);
		rf.setLayout(null);
		rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel();
		title.setBounds(10,1,500,85);
		title.setForeground(Color.black);
		title.setFont(new Font("TimesRoman",1,40));
		title.setText("SE360 Tangram Solver");
		
		JLabel group = new JLabel();
		group.setBounds(10,58,700,85);
		group.setForeground(Color.black);
		group.setFont(new Font("TimesRoman",1,15));
		group.setText("Group 12: Liao Weiyu, Yao Tianyi, Yu Mengke, Fu Zhanchao");

        panel = new JPanel();
		panel.setBounds(511, 10, 750, 750);
		panel.setBackground(Color.white);
		panel.setVisible(true);
		
        JLabel now=new JLabel(String.valueOf(n));   
        now.setBounds(870,769,50,20);
        now.setFont(new Font("DejaVu Sans Mono",1,20));
        JLabel l=new JLabel("/");   
        l.setBounds(910,769,20,20);
        l.setFont(new Font("DejaVu Sans Mono",1,20));
        JLabel total=new JLabel("???");   
        total.setBounds(920,769,50,20);
        total.setFont(new Font("DejaVu Sans Mono",1,20));
		
        JLabel kind=new JLabel("Pentagonal Type：");   
        kind.setBounds(15,162,200,25);
        kind.setFont(new Font("DejaVu Sns Mono",1,20));
        JComboBox<String> type=new JComboBox<String>();   
        type.setBounds(15,195,135,30);
        type.addItem("   --Please choose--"); 
        type.addItem("   Convex pentagon"); //凸
        type.addItem("   Concave pentagon");
        type.addActionListener((e)->{
        	shapek = method.getSelectedIndex();
        });
        
        JLabel alg=new JLabel("Search Method：");   
        alg.setBounds(15,265,200,20);
        alg.setFont(new Font("DejaVu Sans Mono",1,20));
        JComboBox<String> method=new JComboBox<String>();   
        method.setBounds(15,295,135,30);
        method.addItem("   --Please choose--"); 
        method.addItem("              DFS"); 
        method.addItem("              BFS");
        method.addItem("                A*");
        method.addItem("              ide*");
        //监听
        method.addActionListener((e)->{
        	int index = method.getSelectedIndex();
            if (index == 1) {
            	algorithm.dfsSearch();
            	algorithm.displayWhileCalculating(rf, panel,260,350,100);
            	answerList = algorithm.getAnswerList();
            	t = answerList.size();
            	total.setText(String.valueOf(t));
            }if (index == 2) {
            	algorithm.bfsSearch();
            	answerList = algorithm.getAnswerList();
            	t = answerList.size();
            	total.setText(String.valueOf(t));
            }if (index == 3) {
            	algorithm.aStarSearch();
            	answerList = algorithm.getAnswerList();
            	t = answerList.size();
            	total.setText(String.valueOf(t));
            }if (index == 4) {
            	algorithm.aStarSearch();
            	answerList = algorithm.getAnswerList();
            	t = answerList.size();
            	total.setText(String.valueOf(t));
            }
        });
        
		button1 = new JButton();
        button1.setBounds(15,700,135,35);
        button1.setText("Different ways");
        button1.addActionListener((e)->{
        	//String img = path.getText().toString();
        	//plabel.setIcon(new ImageIcon(UI.class.getResource(img+"/1.jpg")));
        	//System.out.println(img+"/1.jpg");
        });
        
        button2 = new JButton();
        button2.setBounds(15,750,135,35);
        button2.setText("Solve");
        button2.addActionListener((e)->{
        	    panel.repaint();
        		Pen pen = new Pen(rf,panel);
        		pen.beforeDraw(answerList.get(n-1).get(0),260,350,100);
        		pen.draw();
       	});       
                
        button4 = new JButton();
        button4.setBounds(980,765,75,25);
        button4.setText("Next");
        button4.addActionListener((e)->{
    		if(n==answerList.size()) {
    			;
    		}else {
    			n++;		
        	    panel.repaint();
        		Pen pen = new Pen(rf,panel);
        		pen.beforeDraw(answerList.get(n-1).get(0),260,350,100);
        		pen.draw();
        		now.setText(String.valueOf(n));
    		}
        });
        
        rf.getContentPane().add(title,null);
        rf.getContentPane().add(group,null);
        rf.getContentPane().add(panel,null);
        rf.getContentPane().add(kind,null);
        rf.getContentPane().add(type,null);
        rf.getContentPane().add(alg,null);
        rf.getContentPane().add(method,null);
        rf.getContentPane().add(button1,null);
        rf.getContentPane().add(button2,null);
        //rf.getContentPane().add(button3,null);
        rf.getContentPane().add(button4,null);
        rf.getContentPane().add(now,null);
        rf.getContentPane().add(total,null);
        rf.getContentPane().add(l,null);
        rf.setVisible(true);
	}
	public static void main(String[] args) {
		new UI().CreateUI();
	}
}