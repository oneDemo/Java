package spring;

public class Student {

    private String name;

    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            ", course=" + course +
            '}';
    }
}
