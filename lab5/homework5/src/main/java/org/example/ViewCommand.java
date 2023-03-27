package org.example;

import org.example.Catalog;
import org.example.Command;
import org.example.Document;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ViewCommand implements Command {
    private Document document;

    public ViewCommand(Document document) {
        this.document = document;
    }

    @Override
    public Catalog execute() throws IOException {
        File file = new File(document.getLocation());
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
        return null;
    }
}
