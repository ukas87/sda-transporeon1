package model;

import java.util.Set;

public class Teacher extends Person {
    public Set<String> getClassTypes() {
        return classTypes;
    }

    public void setClassTypes(Set<String> classTypes) {
        this.classTypes = classTypes;
    }

    private Set<String> classTypes;

    @Override
    public String toString() {
        return "Teacher{" +
                "classTypes=" + classTypes +
                "} " + super.toString();
    }
}
