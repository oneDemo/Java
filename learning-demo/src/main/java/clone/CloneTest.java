package clone;

import org.junit.Test;

public class CloneTest {

    /**
     * 浅复制
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        Student stu1 = new Student();
        stu1.setAge(20);
        stu1.setName("zhangsan");
        stu1.setSex(new StringBuffer("男"));

        School s1 = new School();
        s1.setSchoolName("实验小学");
        s1.setStuNums(100);
        s1.setStu(stu1);

        System.out.println(
            "s1: " + s1 + " s1的hashcode:" + s1.hashCode() + "  s1中stu1的hashcode:" + s1.getStu().hashCode());

        School s2 = s1.clone();  //调用重写的clone方法，clone出一个新的school---s2
        System.out.println(
            "s2: " + s2 + " s2的hashcode:" + s2.hashCode() + " s2中stu1的hashcode:" + s2.getStu().hashCode());

    }

    @Test
    public void test2() {

    }
}
