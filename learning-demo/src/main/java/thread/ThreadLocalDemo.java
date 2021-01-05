package thread;

public class ThreadLocalDemo {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue() {
            System.out.println("调用ThreadLocal的get方法，当线程共享变量没有设置，调用initialValue获取默认值");
            return super.initialValue();
        }
    };

    public static void main(String[] args) {
        //new Thread(new MyIntegerTask("线程1", 10)).start();
        //new Thread(new MyIntegerTask("线程2", 20)).start();
        String abc = String.format(":%s", 12);

        System.out.println(abc);
    }

    public static class MyIntegerTask implements Runnable {
        private String name;
        private Integer index;

        public MyIntegerTask(String name, Integer index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                //ThreadLocal.get方法获取线程变量
                if (null == ThreadLocalDemo.threadLocal.get()) {
                    //获取线程变量为空
                    ThreadLocalDemo.threadLocal.set(index);
                    System.out.println(name + ":设置共享变量值为:" + index);
                } else {
                    int num = (Integer)ThreadLocalDemo.threadLocal.get();
                    ThreadLocalDemo.threadLocal.set(num + 1);
                    System.out.println(name + ":共享变量值为：" + ThreadLocalDemo.threadLocal.get());
                    if (i == 3) {
                        ThreadLocalDemo.threadLocal.remove();
                        System.out.println(name + ":执行remove操作");

                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}