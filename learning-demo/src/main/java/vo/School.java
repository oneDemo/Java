package vo;

public class School implements Cloneable {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "School{" +
            "address='" + address + '\'' +
            '}';
    }
}
