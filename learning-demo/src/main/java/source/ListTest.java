package source;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        list.add(1,"abc");

        List ab=new ArrayList();
        ab.add("a");
        list.removeAll(ab);
        System.out.println(list);
        System.out.println(list.toArray());
        list.iterator();
    }
}
