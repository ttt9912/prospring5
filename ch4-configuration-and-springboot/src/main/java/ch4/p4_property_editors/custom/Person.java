package ch4.p4_property_editors.custom;

public class Person {
    private FullName fullName;

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(final FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName=" + fullName +
                '}';
    }
}
