package nested.anonymous;

import org.junit.Test;

/**
 * 匿名内部类：没有名字的内部类 1：不能定义任何静态成员、方法 2：必须实现接口和抽象父类的所有抽象方法 3：访问外部类的成员变量或方法必须用static修饰
 *
 * @author wangtenglong
 * @date 2020-04-05
 */
public class AnonymousClassTest {

    @Test
    public void test() {

        final People people = new People() {

            //不能定义任何静态成员、方法
            //static Integer a = 1;
            //public static  void method() {
            //
            //}

            @Override
            public void eat() {
                System.out.println("匿名内部类的方法");
            }
        };
        people.eat();
    }
}
