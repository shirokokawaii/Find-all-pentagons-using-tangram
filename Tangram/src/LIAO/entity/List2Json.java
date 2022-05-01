package LIAO.entity;

import LIAO.Shape;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;

import static LIAO.entity.Tangram.*;

public class List2Json {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(S7);
        shapes.add(S3);

        JSONArray shapeJsonArray = new JSONArray();
        shapeJsonArray = JSONArray.parseArray(JSON.toJSONString(shapes));
        System.out.println("\n方式 6: " + shapeJsonArray.toJSONString());

        String pathFile="X:\\javaProject\\Find-all-pentagons-using-tangram\\Tangram";   //注意 这个文件夹要是没有的话是会报错的
        File file=new File(pathFile);
        if (!file.exists()) { //所以在这里必须提前创建好文件夹
            file.mkdirs();
        }


        Path ConfPath = Paths.get(pathFile, "test.json");



    }
}
