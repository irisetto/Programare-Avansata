package org.example;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException, InvalidPathException, TemplateException, EqualIdException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

    }

    private void testCreateSave() throws IOException, InvalidPathException, EqualIdException {
        Catalog catalog =
                new Catalog("MyDocuments");
        Document book = new Document("carte", "Acolo unde canta racii", "D:\\Scoala\\Carti\\Acolo unde cântă racii by Delia Owens (z-lib.org).pdf");
        var article = new Document("doc", "Eseuri", "D:\\Scoala\\ESEURI-eBook-uri -Bacalaureat.pdf");
        AddCommand addCommand = new AddCommand(catalog, book, article);
        addCommand.execute();
        SaveCommand saveCommand = new SaveCommand(catalog, "D:\\Faculty\\II-2\\Programare avansata\\lab5\\catalog.json");
        saveCommand.execute();
    }

    private void testLoadView() throws InvalidCatalogException, IOException, TemplateException {
        LoadCommand loadCommand = new LoadCommand("D:\\Faculty\\II-2\\Programare avansata\\lab5\\catalog.json");
        Catalog catalog = loadCommand.execute();
        ViewCommand viewCommand = new ViewCommand(catalog.findById("doc"));
        viewCommand.execute();
        System.out.println(catalog);
        ListCommand listCommand = new ListCommand(catalog);
        listCommand.execute();
        ReportCommand reportCommand = new ReportCommand(catalog);
        //reportCommand.execute();
    }

}