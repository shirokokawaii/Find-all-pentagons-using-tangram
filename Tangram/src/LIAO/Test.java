package LIAO;

import LIAO.utils.Connector;

import static LIAO.Tangram.*;


public class Test {
    public static void main(String[] args) {
//        System.out.println(S0.getPoint(1));
//        System.out.println(S0.getPoint(2));
//        System.out.println(S0.getPoint(5));
        // System.out.println(Connector.connect(S0, S1, 1, 1, true));
        // System.out.println(Connector.connectAll(S2, S1));

        //*********************************************************
        Algorithm algorithm = new Algorithm(S0, S1, S2, S3, S4, S5, S6, S7);
        algorithm.bfsSearch();
    }
}
