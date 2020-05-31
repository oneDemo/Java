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

    //核心线程池数不能超过最大线程池数
    /**
     * 核心线程池数
     */
    private static final int corePoolSize = 1;

    /**
     * 最大线程池数
     */
    private static final int maximumPoolSize = 1;

    /**
     * 线程池空闲时间： 当前线程池的数量超过corePoolSize时，多余空闲线程的存活时间
     */
    private static final long keepAliveTime = 1;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
        keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));

    public static void main(String[] args) {

        MyTask myTask1 = new MyTask(1, "线程1");
        MyTask myTask2 = new MyTask(2, "线程2");
        MyTask myTask3 = new MyTask(3, "线程3");
        MyTask myTask4 = new MyTask(4, "线程4");
        MyTask myTask5 = new MyTask(5, "线程5");

        threadPoolExecutor.execute(myTask1);
        threadPoolExecutor.execute(myTask2);
        threadPoolExecutor.execute(myTask3);
        threadPoolExecutor.execute(myTask4);
        threadPoolExecutor.execute(myTask5);

        System.out.println("end");

    }

    /**
     * 1. 线程池刚创建时，里面没有一个线程。任务队列是作为参数传进来的。不过，就算队列里面
     * 有任务，线程池也不会马上执行它们。
     * 2. 当调用 execute() 方法添加一个任务时，线程池会做如下判断：
     * a) 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
     * b) 如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列；
     * c) 如果这时候队列满了，而且正在运行的线程数量小于 maximumPoolSize，那么还是要
     * 创建非核心线程立刻运行这个任务；
     * d) 如果队列满了，而且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池
     * 会抛出异常 RejectExecutionException。
     * 3. 当一个线程完成任务时，它会从队列中取下一个任务来执行。
     * 4. 当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程池会判断，如果当前运
     * 行的线程数大于 corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后，它
     * 最终会收缩到 corePoolSize 的大小。
     */
}
