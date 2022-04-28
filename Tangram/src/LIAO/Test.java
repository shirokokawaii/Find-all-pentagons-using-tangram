package LIAO;

import LIAO.utils.Connector;

import static LIAO.Tangram.*;


public class Test {
    public static void main(String[] args) {
        System.out.println(S0.getPoint(1));
        System.out.println(S0.getPoint(2));
        System.out.println(S0.getPoint(5));
        System.out.println(Connector.connect(S0, S1, 2, 0, true));
        System.out.println(Connector.connectAll(S0, S1));
    }
}
