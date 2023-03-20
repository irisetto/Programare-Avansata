package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        Project[] projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);

        List<Student> studentList = new LinkedList<>();
        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList);
        System.out.println(studentList);
        Set<Project> projectSet = new TreeSet<>();
        projectSet.addAll(Arrays.asList(projects));
        System.out.println(projectSet);
        MatchingProblem matchingProblem = new MatchingProblem(students, projects);
        matchingProblem.addAdmissibleProject(students[0], projects[0]);
        matchingProblem.addAdmissibleProject(students[0], projects[1]);
        matchingProblem.addAdmissibleProject(students[0], projects[2]);
        matchingProblem.addAdmissibleProject(students[1], projects[0]);
        matchingProblem.addAdmissibleProject(students[1], projects[1]);
        matchingProblem.addAdmissibleProject(students[2], projects[0]);

        Map<Student, List<Project>> admissible = matchingProblem.getAdmissible();
        double averageNumPreferences = admissible.values()
                .stream()
                .mapToInt(List::size)
                .average()
                .orElse(0.0);
        System.out.println(averageNumPreferences);
        admissible.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() < averageNumPreferences)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
        Faker faker = new Faker();
        Faker fakerProj = new Faker();
        Arrays.stream(students).forEach(s -> s.setName(faker.name().firstName()));
        System.out.println(Arrays.toString(students));
        Arrays.stream(projects).forEach(s -> s.setName(fakerProj.programmingLanguage().name()));
        System.out.println(Arrays.toString(projects));
        matchingProblem.createMatchingGreedy();
        System.out.println(matchingProblem.getMatches());

    }
}