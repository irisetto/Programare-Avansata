package org.example;

import java.util.*;

public class MatchingProblem {
    private Project[] projects;
    private Student[] students;
    private Map<Student, List<Project>> admissible;
    private Map<Project, List<Student>> projectPref;
    private Map<String, String> matches;

    public MatchingProblem(Student[] students, Project[] projects) {
        this.students = students;
        this.projects = projects;
        this.admissible = new HashMap<>();
        this.projectPref = new HashMap<>();
        for (Student student : students) {
            admissible.put(student, new ArrayList<>());
        }
        for (Project project : projects) {
            projectPref.put(project, new ArrayList<>());
        }
        this.matches = new HashMap<>();
    }

    public static int compareByNrStud(Map.Entry<Project, List<Student>> o1, Map.Entry<Project, List<Student>> o2) {
        if (o1.getValue().size() == o2.getValue().size())
            return 0;
        else if (o1.getValue().size() > o2.getValue().size())
            return 1;
        else return -1;
    }

    public void addAdmissibleProject(Student student, Project project) {
        if (admissible.containsKey(student))
            admissible.get(student).add(project);
        if (projectPref.containsKey(project))
            projectPref.get(project).add(student);
    }

    public void createMatchingGreedy() {
        //sortam map projectPref dupa cati studenti vor acel proiect
        List<Map.Entry<Project, List<Student>>> entriesProj = new ArrayList<>(projectPref.entrySet());
        Collections.sort(entriesProj, MatchingProblem::compareByNrStud);
        Map<Project, List<Student>> sortedMapProj = new LinkedHashMap<>();
        for (Map.Entry<Project, List<Student>> entry : entriesProj) {
            sortedMapProj.put(entry.getKey(), entry.getValue());
        }
        System.out.println(sortedMapProj);
        //pt fiecare proiect asignam un student in functie de lista
        Set<Student> availableStudents = new HashSet<>(admissible.keySet());
        for (Map.Entry<Project, List<Student>> entry : sortedMapProj.entrySet()) {
            for (Student s : entry.getValue()) {
                if (availableStudents.contains(s)) {
                    matches.put(s.getName(), entry.getKey().getName());
                    availableStudents.remove(s);
                    break;
                }
            }
        }
    }

    public Map<Student, List<Project>> getAdmissible() {
        return admissible;
    }

    public Map<String, String> getMatches() {
        return matches;
    }
}
