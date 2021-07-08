package stream;

import org.junit.Test;

import java.util.function.Consumer;

public class StreamTest {


    @Test
    public void test1() {

        String a= null;

        StringBuffer sb= new StringBuffer();
        sb.append("aa");
        sb.append(a);

        sb.append("sss");
        int ab=1;
//        processFromRdb(1L, 1, t -> {
//                    int a = 1;
//                    System.out.println(t);
//                }
//
//        );

        for (int i =0;i<4;i++){

            System.out.println(i);
        }

    }

    private void processFromRdb(Long warehouseId, Integer a, Consumer<String> consumer) {

        int b = 1;

        consumer.accept("aa");
    }

}