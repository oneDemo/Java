import org.junit.Test;
import vo.People;
import vo.PeopleInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 引用传递
 *
 * @author wangtenglong
 * @date
 */
public class ObjectPassTest {

    @Test
    public void passTest() {

        List<People> peopleList = new ArrayList<People>();

        People people1 = new People(1);
        people1.setName("小米");

        People people2 = new People(2);
        people2.setName("大米");

        People people3 = new People(3);
        people3.setName("中米");

        peopleList.add(people1);
        peopleList.add(people2);
        peopleList.add(people3);

        peopleList=peopleList.stream().filter(e -> e.getAge() == 1).collect(Collectors.toList());

        int a =1;
        //PeopleInfo peopleInfo = new PeopleInfo();
        //peopleInfo.setPeopleList(peopleList);
        //
        //List<People> peopleList2 = peopleInfo.getPeopleList().stream().filter(e -> e.getAge() == 1).collect(
        //    Collectors.toList());
        //
        //for (People people : peopleList2) {
        //    people.setName("zzz");
        //}
        //Integer a = 1;
        //filter(peopleList, a);
        //
        //System.out.println(a);
        //System.out.println(peopleList);
        //
        //System.out.println(peopleList2);
        //System.out.println(peopleInfo.getPeopleList());
        //Integer b = 1;
    }

    private void filter(List<People> peopleList, Integer a) {

        for (People people : peopleList) {
            people.setName("zzz");
        }
        a = 100;
        //peopleList.stream().filter(e -> e.getAge() == 1).collect(Collectors.toList());
    }
}
