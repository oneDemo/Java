package init;

public class China extends County {

    private Integer age;

    static {
        System.out.println("子类静态方法块初始化");
    }

    public China(Integer age, Integer age1) {
        super(age);
        System.out.println("子类构造方法初始化开始" + age + "-" + age1);
        this.age = age1;
        System.out.println("子类构造方法初始化完成" + age + "-" + age1);

    }
}
