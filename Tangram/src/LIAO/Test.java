package LIAO;

import LIAO.utils.Connector;

import java.util.Collections;
import java.util.List;

import static LIAO.entity.Tangram.*;


public class Test {
    public static void main(String[] args) {

        // System.out.println(Connector.connect(S0, S1, 1, 1, true));
        Connector.connectAll(S2, S1);
        System.out.println();
        System.out.println();
        //Connector.connectAllRe(S2, S1);
        //*********************************************************
        //Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
        //
        //algorithm.bfsSearch();

    }
}
