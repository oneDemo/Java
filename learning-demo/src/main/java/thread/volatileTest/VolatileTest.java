package thread.volatileTest;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VolatileTest {

    //定义线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(2));

    //private static String flag = "初始值";
    //
    //public static String getFlag() {
    //    return flag;
    //}
    //
    //public static void setFlag(String flag) {
    //    VolatileTest.flag = flag;
    //}

    @Test
    public void test() throws Exception {

        TestVolatile testVolatile = new TestVolatile();


        Thread a = new Thread() {
            @Override
            public void run() {


                try {

                    //VolatileTest volatileTest = new VolatileTest();
                    //System.out.println("线程1：" + volatileTest.;


                    testVolatile.changeStatus();
                    Thread.sleep(200);

                    testVolatile.run("线程1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.submit(a);

       Thread b= new Thread() {
            @Override
            public void run() {
                //System.out.println("线程2：" + getFlag());
                //
                //setFlag("又初始了");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                testVolatile.run("线程2");


            }
        };

       executor.submit(b);

        Thread.sleep(5000);

    }

}