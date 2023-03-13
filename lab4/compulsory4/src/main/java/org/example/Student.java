package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private List<Project> admissibleProjects;

    public Student(String name) {
        this.name = name;
        admissibleProjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    public void setAdmissibleProjects(List<Project> admissibleProjects) {
        this.admissibleProjects = admissibleProjects;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Student: " +
                name + "=" + this.getAdmissibleProjects();
    }
}
