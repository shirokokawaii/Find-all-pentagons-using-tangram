package tangram;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * load path填写/img(如果图片在img下）
 * */


public class UI {
	JLabel plabel, title, group, kind, alg, load;
	JPanel panel;
	JComboBox<String> type, method;
	JTextField path;
	JButton button1, button2, button3, button4;
	
	int left,top;
	int x,y;
	int n = 1;
	
	public void CreateUI() {
		JFrame rf=new JFrame("Tangram Solver");
		rf.setBounds(100,100,770,700);
		rf.setLocationRelativeTo(null);
		rf.setResizable(false);
		rf.getContentPane().setLayout(null);
		rf.setBackground(Color.white);
		
		JLabel title = new JLabel();
		title.setBounds(170,10,500,85);
		title.setForeground(Color.black);
		title.setFont(new Font("TimesRoman",1,40));
		title.setText("SE360 Tangram Solver");
		
		JLabel group = new JLabel();
		group.setBounds(164,58,700,85);
		group.setForeground(Color.black);
		group.setFont(new Font("TimesRoman",1,15));
		group.setText("Group 12: Liao Weiyu, Yao Tianyi, Yu Mengke, Fu Zhanchao");
		
        panel = new JPanel();
		panel.setBounds(240, 145, 480, 480);
		JLabel plabel = new JLabel();
		plabel.setBounds(240, 145, 480, 480);
		panel.add(plabel);
		
        JLabel kind=new JLabel("Pentagonal Types：");   
        kind.setBounds(15,162,200,25);
        kind.setFont(new Font("DejaVu Sans Mono",1,20));
        JComboBox<String> type=new JComboBox<String>();   
        type.setBounds(15,195,135,20);
        type.addItem("   --Please choose--"); 
        type.addItem("   Convex pentagon"); //凸
        type.addItem("   Concave pentagon");
        
        JLabel alg=new JLabel("Search Method：");   
        alg.setBounds(15,265,200,20);
        alg.setFont(new Font("DejaVu Sans Mono",1,20));
        JComboBox<String> method=new JComboBox<String>();   
        method.setBounds(15,295,135,20);
        method.addItem("   --Please choose--"); 
        method.addItem("              DFS"); 
        method.addItem("              BFS");
        method.addItem("                A*");
        
        JLabel load=new JLabel("Load from path：");   
        load.setBounds(15,365,200,20);
        load.setFont(new Font("DejaVu Sans Mono",1,20));
        JTextField path=new JTextField();
        path.setBounds(15,396,135,20);
        
		button1 = new JButton();
        button1.setBounds(15,549,90,27);
        button1.setText("Load");
        button1.addActionListener((e)->{
        	String img = path.getText().toString();
        	plabel.setIcon(new ImageIcon(UI.class.getResource(img+"/1.jpg")));
        	System.out.println(img+"/1.jpg");
        });
        
        button2 = new JButton();
        button2.setBounds(15,598,90,27);
        button2.setText("Solve");
        button2.addActionListener((e)->{
    		
        });
        
        button3 = new JButton();
        button3.setBounds(555,630,75,25);
        button3.setText("Return");
        button3.addActionListener((e)->{
    		if(n==1) {
    			;
    		}else {
    			n--;
    			String img = path.getText().toString();
            	plabel.setIcon(new ImageIcon(UI.class.getResource(img+"/"+n+".jpg")));
    		}
        });
        
        button4 = new JButton();
        button4.setBounds(645,630,75,25);
        button4.setText("Next");
        button4.addActionListener((e)->{
    		if(n==55) {
    			;
    		}else {
    			n++;
    			String img = path.getText().toString();
            	plabel.setIcon(new ImageIcon(UI.class.getResource(img+"/"+n+".jpg")));
    		}
        });
        
        rf.getContentPane().add(title,null);
        rf.getContentPane().add(group,null);
        rf.getContentPane().add(panel,null);
        rf.getContentPane().add(kind,null);
        rf.getContentPane().add(type,null);
        rf.getContentPane().add(alg,null);
        rf.getContentPane().add(method,null);
        rf.getContentPane().add(load,null);
        rf.getContentPane().add(path,null);
        rf.getContentPane().add(button1,null);
        rf.getContentPane().add(button2,null);
        rf.getContentPane().add(button3,null);
        rf.getContentPane().add(button4,null);
        rf.setVisible(true);
	}
	public static void main(String[] args) {
		new UI().CreateUI();
	}
}
