import LIAO.Connector;
import LIAO.IDA;
import LIAO.Shape;

import static LIAO.entity.Tangram.*;

public class test {
    public static void main(String[] args) {
        Shape shape = Connector.connect(S0, S7, 0, 0, true);
        Shape shape1 = Connector.connect(shape, S2, 0, 0, true);
        System.out.println(IDA.getContain(shape1));



    }
}
