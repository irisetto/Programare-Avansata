package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Document> docs = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog(String name, List<Document> docs) {
        this.docs = docs;
    }

    public void add(Document doc) {
        docs.add(doc); //muta catalogUtil
    }

    public Document findById(String id) {
        return docs.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Catalog:" + '\n' +
                "name='" + name + '\'' + '\n' +
                "docs=" + '\n' + docs;
    }
}
