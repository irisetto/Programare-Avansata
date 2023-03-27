package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.Catalog;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public Catalog execute() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(new File("D:\\Faculty\\II-2\\Programare avansata\\lab5\\homework5\\"));
        cfg.setDefaultEncoding("UTF-8");

        Map<String, Object> data = new HashMap<>();
        data.put("catalog", catalog);

        Template template = cfg.getTemplate("report.ftl");

        File file = new File("report.html");
        Writer writer = new FileWriter(file);
        template.process(data, writer);
        System.out.println("Report generated successfully.");

        // Open the report in the default browser
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(file.toURI());
        return null;
    }
}
