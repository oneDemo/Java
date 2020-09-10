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

        School school = new School();
        school.setAddress("福建");

        People tom = new People(1);
        tom.setSchool(school);

        People li = new People(2);
        //对象本身深拷贝，对象的引用对象浅拷贝
        BeanUtils.copyProperties(tom, li);

        li.getSchool().setAddress("浙江");

        school.setAddress("浅复制，schoole都被变更了");

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

        school.setAddress("浅复制，schoole都被变更了");
        System.out.println(tom);
        System.out.println(li);

    }

}
