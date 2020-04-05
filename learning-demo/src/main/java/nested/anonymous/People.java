package nested.anonymous;

abstract class People {

    //类的成员
    private Integer age = 1;

    private static Integer num = 2;

    public People() {
        System.out.println("实例化父类");
    }

    public abstract void eat();
}
