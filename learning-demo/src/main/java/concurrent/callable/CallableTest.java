package concurrent.callable;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date
 */
public class CallableTest {

    @Test
    public void test() throws Exception {

        //定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(2));

        Task task = new Task();
        Future result = executor.submit(task);

        System.out.println("submit:" + date2Str());

        //Thread.sleep(2000);
        //System.out.println("主线程同步执行中:" + date2Str());

        System.out.println("start 业务代码块");

        System.out.println("end 业务代码块");

        //future在get的时候属于阻塞状态，会更待task任务执行结束
        System.out.println("等待task执行结束:" + date2Str());

        //result.get：会一直等待线程执行完成
        System.out.println("task运行结果:" + date2Str() + "->" + result.get());

        System.out.println("执行结束:" + date2Str());
    }

    /**
     * 线程任务
     */
    class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            System.out.println("子线程计算begin:" + date2Str());
            Thread.sleep(30000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("子线程计算end:" + date2Str());

            return sum;
        }
    }

    private static String date2Str() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
