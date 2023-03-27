package org.example;

import org.example.Catalog;
import org.example.Command;
import org.example.Document;

import java.io.IOException;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public Catalog execute() throws IOException {
        System.out.println("Documents in the catalog " + catalog.getName() + " :");
        for (Document doc : catalog.getDocs()) {
            System.out.println(doc);
        }
        return null;
    }
}
