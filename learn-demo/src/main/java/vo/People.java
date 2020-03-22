package vo;

import java.io.Serializable;

/**
 * @author wangtenglong
 * @date 2020-03-21
 */
public class People implements Comparable<People>, Serializable {

    private Integer age;

    public People(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(People o) {
        return this.age - o.getAge();
    }

    @Override
    public String toString() {
        return "People{" +
            "age=" + age +
            '}';
    }

}
