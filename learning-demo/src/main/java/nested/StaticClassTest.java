package nested;

import org.junit.Test;

/**
 * 静态内部类：被static修饰的成员内部类
 *
 * @author wangtenglong
 * @date 2020-04-05
 */
public class StaticClassTest {

    //类的成员
    private Integer age = 1;

    private static Integer num = 2;

    public static void staticTest() {
        System.out.println("外部类静态方法");
    }

    static class StaticInnerClass {

        //静态内部类可以定义静态和非静态的属性和方法
        int a = 0;//非静态变量
        static int b = 0;//静态变量（与成员内部类的区别）

        public static void staticMethod() {
            //Integer c = age;编译报错（不能访问外部类非静态的属性和方法）
            //静态内部类属于外部类，不属于外部类对象
            System.out.println("静态内部类访问外部类的静态成员属性" + num);
            staticTest();
        }

    }

    @Test
    public void test() {
        //静态内部类属于外部类，不属于外部类对象,不需要通过外部类访问。与成员内部内的区别
        StaticInnerClass staticInnerClass = new StaticInnerClass();

        //外部类访问静态内部类
        //静态变量-方法  --方式一
        StaticInnerClass.staticMethod();
        //静态变量-方法  --方式二
        StaticClassTest.StaticInnerClass.staticMethod();
        //非静态--方式1
        Integer z = new StaticInnerClass().a;
        //非静态--方式2
        Integer f = new StaticClassTest.StaticInnerClass().a;
    }
}
