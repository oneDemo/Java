package thread.threadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author wangtenglong
 * @date 2020-03-21
 */
public class ThreadPoolExecutorDemo {

    /**
     * 核心线程池数
     */
    private static final int corePoolSize = 2;

    /**
     * 最大线程池数
     */
    private static final int maximumPoolSize = 3;

    /**
     * 线程池空闲时间： 当前线程池的数量超过corePoolSize时，多余空闲线程的存活时间
     */
    private static final long keepAliveTime = 1;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
        keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));

    public static void main(String[] args) {

        MyTask myTask1 = new MyTask(1, "线程1");
        MyTask myTask2 = new MyTask(2, "线程2");
        MyTask myTask3 = new MyTask(4, "线程3");
        MyTask myTask4 = new MyTask(6, "线程4");
        MyTask myTask5 = new MyTask(5, "线程5");

        threadPoolExecutor.execute(myTask1);
        threadPoolExecutor.execute(myTask2);
        threadPoolExecutor.execute(myTask3);
        threadPoolExecutor.execute(myTask4);
        threadPoolExecutor.execute(myTask5);

    }
}
