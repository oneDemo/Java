package other;

import org.junit.Test;

public class RefTest {

    class Value {
        public int i = 15;
    }

    public void first() {
        int i = 5;
        Value v = new Value();
        v.i = 25;

        System.out.println(v);
        second(v, i);
        System.out.println(v);

        System.out.println(v.i);
    }

    public void second(Value v, int i) {
        i = 0;
        v.i = 20;
        System.out.println(v);

        Value value = new Value();
        v = value;//形参v指向了value，但是first还是指向原来的对象

        System.out.println(value);

        System.out.println(v);

        System.out.println(v.i + "" + i);

    }

    @Test
    public void test1() {

        RefTest refTest = new RefTest();
        refTest.first();
    }
}
