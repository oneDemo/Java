package nested;

import org.junit.Test;

/**
 * 局部内部类demo 概念：定义在类的成员方法中
 *
 * @author wangtenglong
 * @date 2020-04-05
 */
public class LocalClassTest {

    //局部内部类只在当前方法有效
    //InnerClass innerClass = new InnerClass();无法访问

    private Integer size = 1;
    static Integer weight = 2;

    public void testMethod() {
        //局部变量
        Integer num = 3;

        //局部变量
        final Integer age = 4;
        /**
         *局部内部类:1:和局部变量一样不能使用任何修饰符（public、private、protected）；
         */
        class InnerClass {

            public InnerClass() {
                System.out.println("局部内部类和局部变量一样不能使用任何修饰符（public、private、protected）");
            }

            //局部内部类不能定义static成员（类的初始化顺序反推）
            //public static int a = 1;
            public void innerMethod() {
                //2：只能访问方法中的final类型变量
                System.out.println("内部类访问方法中的final类型变量" + age);

                //int c = num;报错
                //局部内部类可以访问外部类的所有成员
                System.out.println("内部类访问外部类的成员" + size);
                System.out.println("内部类访问外部类的成员" + weight);

            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.innerMethod();
    }

    @Test
    public void test() {

        this.testMethod();

    }
}
