package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        students[3].setName("Andrei");

        Project[] projects = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);
        projects[3].setName("A1");
        students[0].setAdmissibleProjects(Arrays.asList(projects[0], projects[1], projects[2]));
        students[1].setAdmissibleProjects(Arrays.asList(projects[0], projects[1]));
        students[2].setAdmissibleProjects(Arrays.asList(projects[0]));

        List<Student> studentList = new LinkedList<>();
        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList);
        System.out.println(studentList);
        Set<Project> projectSet = new TreeSet<>();
        projectSet.addAll(Arrays.asList(projects));
        System.out.println(projectSet);
    }
}