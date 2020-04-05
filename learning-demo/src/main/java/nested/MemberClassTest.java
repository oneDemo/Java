package nested;

import org.junit.Test;

/**
 * 成员内部类：定义在类的内部（类的一个成员）
 *
 * @author wangtenglong
 * @date 2020-04-05
 */
public class MemberClassTest {

    //类的成员
    private Integer age = 1;

    private static Integer num = 2;

    /**
     * 成员内部类:可以被任何的访问修饰符访问，因为它也是类的成员
     */
    class Member {

        private Integer age = 10;
        //不能定义static修饰的属性和方法：
        //因为：成员内部类是外部类的一个成员，只有当外部类初始化的时候，内部类才能初始化。
        //静态变量属于类级别，在类加载的时候就初始化。所有两者在语法上是有矛盾的。
        //static {
        //
        //}报错

        //为什么可以定义static final修饰的变量？　
        //首先要先知道static final修饰的变量叫做常量，常量分为编译期常量和非编译期常量
        // 编译期常量：在程序编译阶段【不需要加载类的字节码】，就可以确定常量的值
        // 非编译期常量：在程序运行阶段【需要加载类的字节码】，可以确定常量的值
        // b属性是静态的，它需要加载类的字节码Member类，由于它是在MemberClassTest内部，需要外部类实例化才能加载，但是调用处没有实例化外部类，所以Member也不会类加载，所以报错！！
        // c属性是编译期常量，它不需要加载类的字节码Member类，所以不会报错
        // d属性是非编译期常量。它需要加载类的字节码Member类，所以报错！！

        int a = 0;//非静态变量
        //static int b = 0;//静态变量【报错！！】
        static final int c = 0;//编译期常量，不需要类加载
        //static final Integer d = new Integer(2);//非编译期常量，需要类加载【报错！！】

        public void memberMethod() {
            //成员内部类访问外部类：可以访问外部类的所有属性和方法（包括静态、私有的）
            System.out.println("成员内部类访问外部类的私有成员属性" + MemberClassTest.this.age);
            System.out.println("直接写属性名字会发生隐藏现象，实际访问的是内部类的属性" + age);//直接写属性名字会发生隐藏现象，实际访问的是内部类的属性
            System.out.println("成员内部类访问外部类的私有静态成员属性" + num);
        }
    }

    @Test
    public void test() {

        //外部类访问内部类:创建内部类需要先创建外部类
        MemberClassTest.Member memberClassTest = new MemberClassTest().new Member();
        memberClassTest.memberMethod();

    }

}
