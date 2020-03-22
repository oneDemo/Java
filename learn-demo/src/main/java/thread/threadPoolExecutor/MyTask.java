package thread.threadPoolExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangtenglong
 * @date 2020-03-21
 */
public class MyTask implements Runnable {

    private int taskId;

    private String taskName;

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {

        try {
            System.out.println("run taskId =" + this.taskId + "   Thread=" + this.taskName);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

            System.out.println(dateFormat.format(new Date()));

            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


