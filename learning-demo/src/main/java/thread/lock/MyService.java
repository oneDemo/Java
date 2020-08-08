package thread.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class MyService {

    //private static Lock lock = new ReentrantLock();
    ////Lock lock=new ReentrantLock(true);//公平锁
    ////Lock lock=new ReentrantLock(false);//非公平锁
    //private static Condition condition = lock.newCondition();//创建 Condition
    //
    //public static void main(String[] args) {
    //
    //    try {
    //        lock.lock();//lock 加锁
    //        //1：wait 方法等待：
    //        //System.out.println("开始 wait");
    //        condition.await();
    //        //通过创建 Condition 对象来使线程 wait，必须先执行 lock.lock 方法获得锁
    //        //:2：signal 方法唤醒
    //        condition.signal();//condition 对象的 signal 方法可以唤醒 wait 线程
    //        for (int i = 0; i < 5; i++) {
    //            System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
    //        }
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    } finally {
    //
    //        lock.unlock();
    //    }
    //}

    @Test
    public void Test() {

        ReentrantLock lock = new ReentrantLock();

        for (int i = 1; i <= 3; i++) {
            lock.lock();
            System.out.println("第" + i + "次获取锁");
        }

        for (int i = 1; i <= 3; i++) {
            try {

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        List list  =new ArrayList<>();

        list.stream().collect(Collectors.toList());
    }

}