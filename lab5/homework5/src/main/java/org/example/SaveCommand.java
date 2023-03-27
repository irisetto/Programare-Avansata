package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Catalog;
import org.example.Command;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String path;

    public SaveCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public Catalog execute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
        return null;
    }
}
