package LIAO;

import LIAO.entity.DrawOutline;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import javax.swing.*;
import java.io.*;



import java.util.ArrayList;

import static LIAO.entity.Tangram.*;

public class List2Json {
    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
            shapes.add(S7);
            shapes.add(S3);
            shapes.add(Connector.connect(S3,S7, 0, 0, true));

//        AlgorithmTest algorithm = new AlgorithmTest(S0, S1, S2, S3, S4, S5, S6, S7);
//        algorithm.bfsSearch(S6);
//        JSONArray shapeJsonArray = new JSONArray();
//        shapeJsonArray = JSONArray.parseArray(JSON.toJSONString(algorithm.answerSet));

//        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
//       algorithm.bfsSearch(S6);
//        algorithm.bfsSearch(S7);



        //JsonArray
        //JSONArray shapeJsonArray = new JSONArray();
        //Shapes -> JsonString -> JsonArray
        //shapeJsonArray = JSONArray.parseArray(JSON.toJSONString(shapes));


//        System.out.println("\nArraytoJson " + shapeJsonArray.toJSONString());

        String pathFile="X:/javaProject/Find-all-pentagons-using-tangram/Tangram/";
        String fileName = pathFile + "result.json";


        //
        //ArrayList<Shape> list = (ArrayList<Shape>) JSON.parseArray(shapeJsonArray.toJSONString(), Shape.class);
        //System.out.println(list);

//        try {
//            writeFile(fileName, shapeJsonArray.toString());
//            System.out.println();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        JSONArray readJsonArray = JSONArray.parseArray(JSON.toJSONString(ReadFile(fileName)));
        ArrayList<Shape> list = (ArrayList<Shape>) JSON.parseArray(readJsonArray.toJSONString(), Shape.class);

        System.out.println("!!"+list);


        //list-1
        JFrame jf = new JFrame("可视化");
        JPanel jpanel = new JPanel();
        jf.add(jpanel);
        jpanel.setSize(500, 500);
        jf.setResizable(true);
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        DrawOutline p = new DrawOutline(jpanel);
        p.draw(list.get(3));



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

