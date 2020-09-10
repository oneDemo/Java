package clone;

public class Student {

    private String name;   //姓名
    private int age;       //年龄
    private StringBuffer sex;  //性别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StringBuffer getSex() {
        return sex;
    }

    public void setSex(StringBuffer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }

}
