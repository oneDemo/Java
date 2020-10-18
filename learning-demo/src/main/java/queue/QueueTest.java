package queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

    @Test
    public void test1()  {

        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

        try {
            blockingQueue.put("a");


        blockingQueue.put("b");

        blockingQueue.put("c");


        blockingQueue.offer("e");

        blockingQueue.offer("f");

        blockingQueue.size();

        Object o = blockingQueue.poll();

        int a = blockingQueue.size();

        int b = 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
