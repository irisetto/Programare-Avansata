package org.example;

import org.graph4j.alg.bipartite.BipartitionAlgorithm;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;
import org.graph4j.util.StableSet;
import org.graph4j.util.VertexSet;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.*;

import java.util.*;
import org.graph4j.*;

public class MatchingProblem {
    private Project[] projects;
    private Student[] students;
    private Map<Student, List<Project>> admissible;
    private Map<Project, List<Student>> projectPref;
    private Map<String, String> matches;
    private Map<String, String> matchesHopcroft;
    private Map<String, String> matchesGraph4J;
    private VertexSet minimumVertexCover;
    private VertexSet maximumStableSet ;

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
        this.matchesHopcroft = new HashMap<>();
        this.matchesGraph4J = new HashMap<>();
    }
    public void addAdmissibleProject(Student student, Project project){
        if(admissible.containsKey(student))
            admissible.get(student).add(project);
        if(projectPref.containsKey(project))
            projectPref.get(project).add(student);
    }
    public static int compareByNrStud(Map.Entry<Project, List<Student>> o1, Map.Entry<Project, List<Student>> o2) {
        if (o1.getValue().size() == o2.getValue().size())
            return 0;
        else if (o1.getValue().size() > o2.getValue().size())
            return 1;
        else return -1;
    }
    public void createMatchingGreedy() {
        //sortam map projectPref dupa cati studenti vor acel proiect
        List<Map.Entry<Project, List<Student>>> entriesProj = new ArrayList<>(projectPref.entrySet());
        Collections.sort(entriesProj, MatchingProblem :: compareByNrStud);
        Map<Project, List<Student>> sortedMapProj = new LinkedHashMap<>();
        for (Map.Entry<Project, List<Student>> entry : entriesProj) {
            sortedMapProj.put(entry.getKey(), entry.getValue());
        }
        //System.out.println(sortedMapProj);
        System.out.println(getAdmissible());
        //pt fiecare proiect asignam un student in functie de lista
        Set<Student> availableStudents = new HashSet<>(admissible.keySet());
        for (Map.Entry<Project, List<Student>> entry : sortedMapProj.entrySet()) {
            for (Student s : entry.getValue()) {
                if(availableStudents.contains(s)) {
                    matches.put(s.getName(), entry.getKey().getName());
                    availableStudents.remove(s);
                     break;
                }
            }
        }
    }
    public void createMatchingJGraphT() {
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Arrays.stream(students).forEach(s -> graph.addVertex(s.getName()));
        Arrays.stream(projects).forEach(p -> graph.addVertex(p.getName()));

        //adaugam muchie pt toate preferintele
        for (Map.Entry<Student, List<Project>> entry : admissible.entrySet()) {
            for (Project p : entry.getValue()) {
              graph.addEdge(entry.getKey().getName(), p.getName());

            }
        }

        Set<String> projectsSet = new HashSet<>();
        for (Project project : projects)
            projectsSet.add(project.getName());
        Set<String> studentsSet = new HashSet<String>();
        for (Student student : students)
            studentsSet.add(student.getName());
        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> matching =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge>(graph, studentsSet, projectsSet);
        MatchingAlgorithm.Matching<String, DefaultEdge> result = matching.getMatching();
        for (DefaultEdge e : result.getEdges()) {
            //System.out.println(graph.getEdgeSource(e) + "-" + graph.getEdgeTarget(e));
            matchesHopcroft.put(graph.getEdgeSource(e),graph.getEdgeTarget(e));
        }
    }

    public Map<String, String> getMatchesGraph4J() {
        return matchesGraph4J;
    }

    public void createMatchingGraph4J() {
        org.graph4j.Graph graph = GraphBuilder.empty().buildGraph();
        Arrays.stream(students).forEach(s -> graph.addVertex(s.getName()));
        Arrays.stream(projects).forEach(p -> graph.addVertex(p.getName()));
        org.graph4j.Graph graphB;
        graphB = GraphBuilder.verticesFrom(graph).buildGraph();

//        StableSet projectsSet = new StableSet(graph);
//        for (int vertex : graph.vertices())
//            if(vertex >= graph.numVertices()/2)
//                projectsSet.add(vertex);
//        StableSet studentsSet = new StableSet(graph);
//        for (int vertex : graph.vertices())
//            if(vertex < graph.numVertices()/2)
//                studentsSet.add(vertex);
        for (Map.Entry<Student, List<Project>> entry : admissible.entrySet()) {
            for (Project p : entry.getValue()) {
              graphB.addEdge(entry.getKey().getName(),p.getName());

            }
        }
        BipartitionAlgorithm bipartite = BipartitionAlgorithm.getInstance(graphB);
        StableSet studentsSet = bipartite.getLeftSide();
        StableSet projectsSet = bipartite.getRightSide();

        HopcroftKarpMaximumMatching matching = new HopcroftKarpMaximumMatching(graphB,studentsSet,projectsSet);
       Matching result = matching.getMatching();
       maximumStableSet = matching.getMaximumStableSet();
       minimumVertexCover = matching.getMinimumVertexCover();
       // System.out.println(Arrays.deepToString(result.edges()));
        for (int[] e:
                result.edges()) {

            matchesGraph4J.put((String) graphB.getVertexLabel(e[0]), (String) graphB.getVertexLabel(e[1]));
        }
       // System.out.println(matchesGraph4J);

    }

    public VertexSet getMinimumVertexCover() {
        return minimumVertexCover;
    }

    public VertexSet getMaximumStableSet() {
        return maximumStableSet;
    }

    public Map<Student, List<Project>> getAdmissible() {
        return admissible;
    }
    //public List<Project>

    public Map<String, String> getMatches() {
        return matches;
    }

    public Map<String, String> getMatchesHopcroft() {
        return matchesHopcroft;
    }

    public void setAdmissible(Map<Student, List<Project>> admissible) {
        this.admissible = admissible;
    }

    public void setProjectPref(Map<Project, List<Student>> projectPref) {
        this.projectPref = projectPref;
    }
}
