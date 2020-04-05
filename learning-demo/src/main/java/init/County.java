package init;

public class County {

    private Integer age;

    static {
        System.out.println("父类静态方法块初始化");
    }

    public County(Integer age) {

        System.out.println("父类构建方法初始化" + age);
        this.age = age;
    }
}
