package LIAO;

import LIAO.entity.DrawOutline;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.io.*;



import java.util.ArrayList;

import static LIAO.entity.Tangram.*;

public class List2Json {
    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(S7);
        shapes.add(S3);
        shapes.add(S2);


        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
        //algorithm.bfsSearch(S6);
        //algorithm.bfsSearch(S7);

        JSONArray shapeJsonArray = new JSONArray();

        shapeJsonArray = JSONArray.parseArray(JSON.toJSONString(shapes));

        System.out.println("\n方式 6: " + shapeJsonArray.toJSONString());

        String pathFile="X:/javaProject/Find-all-pentagons-using-tangram/Tangram/";
        String fileName = pathFile + "result.json";
        //ArrayList<Shape> list = (ArrayList<Shape>) shapeJsonArray.toJavaList(Shape.class);
        ArrayList<Shape> list = (ArrayList<Shape>) JSON.parseArray(shapeJsonArray.toJSONString(), Shape.class);

        System.out.println(list);

        try {
            writeFile(fileName, shapeJsonArray.toString());
            ReadFile(fileName);



            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }





        JFrame jf = new JFrame("图形可视化工具");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(1000, 1000);
        jf.setResizable(false);
        jf.setSize(1000, 1000); //设置窗口大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//意思就是设置一个默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
        jf.setVisible(true);// 可视化 显示在屏幕上
        DrawOutline p = new DrawOutline(jpanel);
        p.draw(list.get(1));

    }

    public static void writeFile(String filePath, String sets)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }


    public static Object ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return JSON.parse(laststr);
    }


}

