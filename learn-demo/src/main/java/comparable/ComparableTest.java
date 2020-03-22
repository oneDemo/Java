package comparable;

import vo.People;

/**
 * @author wangtenglong
 * @date 2020-03-21
 */
public class ComparableTest {

    /***
     * comparable排序接口
     * compareTo方法：比较此对象与指定对象的大小。如果改对象小于、等于、大于指定对象，则返回-1，0，1
     * 实现了Comparable接口的类的对象的列表或数组，可以通过Collections.sort或Arrays.sort进行自动排序。
     * @param args
     */
    public static void main(String[] args) {

        People tom = new People(5);

        People alice = new People(6);

        System.out.println(tom.compareTo(alice));
    }
}
