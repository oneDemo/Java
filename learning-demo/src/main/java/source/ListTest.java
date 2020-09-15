package source;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ListTest {

    /**
     * add源码分析
     */
    @Test
    public void testListAdd() {

        int oldCapacity = 3;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        List list = new ArrayList();

        //
        //   private void grow(int minCapacity) {
        //        // overflow-conscious code
        //        int oldCapacity = elementData.length;
        //        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //        if (newCapacity - minCapacity < 0)
        //            newCapacity = minCapacity;
        //        if (newCapacity - MAX_ARRAY_SIZE > 0)
        //            newCapacity = hugeCapacity(minCapacity);
        //        // minCapacity is usually close to size, so this is a win:
        //        elementData = Arrays.copyOf(elementData, newCapacity);
        //    }
        for (int i = 0; i < 11; i++) {

            list.add("a" + i);
        }

        list.indexOf("a2");

        list.remove("a5");

        list.get(4);

        list.add(1, "abc");

        List ab = new ArrayList();
        ab.add("a");
        list.removeAll(ab);
        System.out.println(list);
        System.out.println(list.toArray());
        list.iterator();
    }

    @Test
    public void test01() {
        Vector vector = new Vector();

        vector.add("a");

        vector.add("b");

        vector.add("c");

        vector.get(0);
    }

    @Test
    public void test2() {

        List linkedList = new LinkedList();

        linkedList.add("a");

        linkedList.add("b");

        linkedList.add("c");

        linkedList.get(2);

        linkedList.remove("b");
        linkedList.remove(1);
    }

    @Test
    public void test3() {

        HashSet set = new HashSet();

        set.add("a");

        set.add("b");

        set.add("c");

        set.remove("b");

        if (set.iterator().hasNext()) {

            set.iterator().next();
        }

    }

    @Test
    public void test4() {

        TreeSet treeSet = new TreeSet();

        treeSet.add("a");

        treeSet.add("b");

        treeSet.add("c");

        treeSet.remove("b");

        treeSet.last();

        treeSet.size();
    }

    @Test
    public void test5() {

        LinkedHashSet linkedHashMap = new LinkedHashSet<>();

        linkedHashMap.add("a");

        linkedHashMap.add("b");

        linkedHashMap.add("c");

        linkedHashMap.remove('b');
    }

    @Test
    public void test6() {

        HashMap hashMap = new HashMap();

        hashMap.put(1, "a");

        hashMap.put(2, "b");

        hashMap.put(3, "c");

        hashMap.get(2);
    }

    @Test
    public void test7() {

        Hashtable hashtable = new Hashtable();

        hashtable.put(123, "a");

        hashtable.put(223, "b");

        hashtable.put(323, "c");

        hashtable.get(123);

    }

    @Test
    public void test8() {

        TreeMap treeMap = new TreeMap();

        treeMap.put(123, "a");

        treeMap.put(223, "b");

        treeMap.put(323, "c");

        treeMap.get(123);
    }

    @Test
    public void test9() {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put(123, "a");

        concurrentHashMap.put(223, "b");

        concurrentHashMap.put(323, "c");

        concurrentHashMap.get(123);
    }
}

