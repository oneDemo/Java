package comparator;

import vo.People;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author wangtenglong
 * @date 2020-03-21
 */
public class PeopleComparator implements Comparator<People> {

    /**
     * Comparator是比较接口，我们如果需要控制某个类的次序， 而该类本身不支持排序(即没有实现Comparable接口)那么我们就可以建立一个“该类的比较器”来进行排序，
     * 这个“比较器”只需要实现Comparator接口即可。 也就是说，我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序。该接口定义如下：
     * <p>
     * package java.util;
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(People o1, People o2) {
        return o1.getAge() - o2.getAge();
    }

    public static void main(String[] args) {

        People[] peoples = new People[] {new People(2), new People(1), new People(3)};
        System.out.println("排序前");
        for (People person : peoples) {
            System.out.print(person.getAge());
        }
        Arrays.sort(peoples, new PeopleComparator());

        System.out.println("\n排序后");
        for (People person : peoples) {
            System.out.print(person.getAge());
        }
    }

    /**
     * Comparable和Comparator区别比较
     * 　　Comparable是排序接口，若一个类实现了Comparable接口，就意味着“该类支持排序”。
     *    Comparator是比较器，我们若需要控制某个类的次序，可以建立一个“该类的比较器”来进行排序。
     *
     * 　　Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。
     *
     * 　　两种方法各有优劣， 用Comparable简单，只要实现Comparable接口的对象直接就成为一个可以比较的对象，但是需要修改源代码。
     *    用Comparator的好处是不需要修改源代码，而是另外实现一个比较器， 当某个自定义的对象需要作比较的时候，把比较器和对象一起传递过去就可以比大小了，
     *    并且在Comparator里面用户可以自己实现复杂的可以通用的逻辑，使其可以匹配一些比较简单的对象，那样就可以节省很多重复劳动了。
     */
}
