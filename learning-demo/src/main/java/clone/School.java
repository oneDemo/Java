package clone;

public class School implements Cloneable {
    private String schoolName;   //学校名称
    private int stuNums;         //学校人数
    private Student stu;         //一个学生

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStuNums() {
        return stuNums;
    }

    public void setStuNums(int stuNums) {
        this.stuNums = stuNums;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    protected School clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (School)super.clone();
    }

    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", stuNums=" + stuNums + ", stu=" + stu + "]";
    }

}


