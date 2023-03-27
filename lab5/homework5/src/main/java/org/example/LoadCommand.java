package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Catalog;
import org.example.Command;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    private String path;

    public LoadCommand(String path) {
        this.path = path;
    }

    @Override
    public Catalog execute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = objectMapper.readValue(
                new File(path),
                Catalog.class);
        return catalog;
    }
}
