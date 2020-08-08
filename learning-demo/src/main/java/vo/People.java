package vo;

import java.io.Serializable;

/**
 * @author wangtenglong
 * @date 2020-03-21
 */
public class People implements Comparable<People>, Serializable, Cloneable {

    private Integer age;

    private String name;

    private School school;

    public People(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(People o) {
        return this.age - o.getAge();
    }

    //@Override
    //public String toString() {
    //    return "People{" +
    //        "age=" + age +
    //        ", name='" + name + '\'' +
    //        ", school=" + school +
    //        '}';
    //}
}
