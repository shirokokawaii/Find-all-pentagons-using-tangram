package LIAO;

import LIAO.Algorithm;
import LIAO.Shape;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;

import static LIAO.entity.Tangram.*;

public class List2Json {
    public static void main(String[] args) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(S7);
        shapes.add(S3);
        shapes.add(S2);


         Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);

         algorithm.bfsSearch(S6);
         algorithm.bfsSearch(S7);

        JSONArray shapeJsonArray = new JSONArray();
        shapeJsonArray = JSONArray.parseArray(JSON.toJSONString(algorithm.answerSet));
        System.out.println("\n方式 6: " + shapeJsonArray.toJSONString());

        String pathFile="X:/javaProject/Find-all-pentagons-using-tangram/Tangram/";
        String fileName = pathFile + "result.json";

        try {
            writeFile(fileName, shapeJsonArray.toString());
//            ReadFile("D:\\8.json");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

