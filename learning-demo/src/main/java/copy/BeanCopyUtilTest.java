package copy;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import vo.People;
import vo.School;

/**
 * @author wangtenglong
 * @date 2020-04-07
 */
public class BeanCopyUtilTest {

    @Test
    public void copyTest() {
        People tom = new People(1);
        School school = new School();
        school.setAddress("福建");
        tom.setSchool(school);

        People li = new People(2);
        //深复制
        BeanUtils.copyProperties(tom, li);
        li.getSchool().setAddress("浙江");

        System.out.println(tom);
        System.out.println(li);
    }

    @Test
    public void copyTest1() throws Exception {
        People tom = new People(1);
        School school = new School();
        school.setAddress("福建");
        tom.setSchool(school);

        People li = (People)tom.clone();
        //浅复制。对象里面的对象变量，复制的是引用
        li.getSchool().setAddress("浙江");
        li.setAge(2);

        System.out.println(tom);
        System.out.println(li);

    }

}
