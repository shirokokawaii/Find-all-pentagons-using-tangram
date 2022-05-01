package LIAO.entity;
//import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson2.*;


import netscape.javascript.JSObject;

import static LIAO.entity.Tangram.S4;
import static LIAO.entity.Tangram.S5;

public class List2Json {
    public static void main(String[] args) {
        String jsonOutput= JSON.toJSONString(S4);
        System.out.println(jsonOutput);
    }
}
