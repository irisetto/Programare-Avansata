package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static Student[] allStudents ;
    public static Project[] allProjects ;
    private static Map<Project, List<Student>> projectPref = new HashMap<>();

    public static void main(String[] args) {
//        var students = IntStream.rangeClosed(0, 2)
//                .mapToObj(i -> new Student("S" + i))
//                .toArray(Student[]::new);
//        Project[] projects = IntStream.rangeClosed(0, 2)
//                .mapToObj(i -> new Project("P" + i))
//                .toArray(Project[]::new);
//
//        List<Student> studentList = new LinkedList<>();
//        studentList.addAll(Arrays.asList(students));
//        Collections.sort(studentList);
//        //System.out.println(studentList);
//        Set<Project> projectSet = new TreeSet<>();
//        projectSet.addAll(Arrays.asList(projects));
//        //System.out.println(projectSet);
//
//        matchingProblem.addAdmissibleProject(students[0], projects[0]);
//        matchingProblem.addAdmissibleProject(students[0], projects[1]);
//        matchingProblem.addAdmissibleProject(students[1], projects[1]);
//        matchingProblem.addAdmissibleProject(students[1], projects[2]);
//        matchingProblem.addAdmissibleProject(students[2], projects[2]);
//        matchingProblem.addAdmissibleProject(students[2], projects[0]);
        int numStudents = 5;
        int numProjects = 5;
        allStudents = new Student[numStudents];
        allProjects = new Project[numProjects];
         Map<Student, List<Project>> admissible = generateRandomProblem(numStudents,numProjects);
        MatchingProblem matchingProblem = new MatchingProblem(allStudents, allProjects);
        matchingProblem.setAdmissible(admissible);
        matchingProblem.setProjectPref(projectPref);
        matchingProblem.createMatchingGreedy();
        System.out.println("Greedy Algorithm: "+matchingProblem.getMatches());
        matchingProblem.createMatchingJGraphT();
        System.out.println("JGraphT library algorithm: "+matchingProblem.getMatchesHopcroft());
        matchingProblem.createMatchingGraph4J();
        System.out.println("Graph4J library algorithm: "+matchingProblem.getMatchesGraph4J());
        System.out.println("Minimum Cardinality Set: " + matchingProblem.getMinimumVertexCover());
        System.out.println("Maximum Cardinality Set: " + matchingProblem.getMaximumStableSet());
    }
    private static Map<Student,List<Project>> generateRandomProblem(int numStudents, int numProjects){
        Map<Student, List<Project>> admissible = new TreeMap<>();

        Random random = new Random();
        //generate projects
        for (int j = 0; j < numProjects; j++) {
            Project project = new Project("P" + j);

            allProjects[j] = project;

        }
        // Generate students and random projects for each one
        for (int i = 0; i < numStudents; i++) {
            Student student = new Student("S" + i);
            allStudents[i] = student;
            List<Project> projects = new ArrayList<>();
            int numAdmissibleProjects = random.nextInt(numProjects) + 1;
            if(i==(numStudents-1)) numAdmissibleProjects=numProjects;
            for (int j = 0; j < numAdmissibleProjects; j++) {

                projects.add(allProjects[j]);
                projectPref.computeIfAbsent(allProjects[j], k -> new ArrayList<>()).add(student);

            }
            admissible.put(student, projects);
        }
        System.out.println(Arrays.toString(allStudents));
        System.out.println(Arrays.toString(allProjects));
        //System.out.println(projectPref);
        return admissible;
    }
}