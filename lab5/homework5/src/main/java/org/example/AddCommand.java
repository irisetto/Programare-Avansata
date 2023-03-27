package org.example;

import org.example.Catalog;
import org.example.Document;

import java.util.Arrays;
import java.util.List;

public class AddCommand implements Command {
    private Catalog catalog;
    private List<Document> docs;

    public AddCommand(Catalog catalog, Document... docs) {
        this.catalog = catalog;
        this.docs = Arrays.asList(docs);
    }

    @Override
    public Catalog execute() {
        for (Document doc : docs) {
            catalog.getDocs().add(doc);
        }

        return null;
    }
}
