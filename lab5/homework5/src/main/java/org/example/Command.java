package org.example;

import freemarker.template.TemplateException;
import org.example.Catalog;

import java.io.IOException;

public interface Command {
    Catalog execute() throws IOException, TemplateException;
}
