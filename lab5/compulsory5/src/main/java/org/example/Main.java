package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();

    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("book1", "Acolo unde canta racii", "D:\\Scoaaa\\Carti");
        var article = new Document("article1", "Eseuri", "D:\\Scoala");
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog, "D:\\Faculty\\II-2\\Programare avansata\\lab5\\catalog.json");
    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("D:\\Faculty\\II-2\\Programare avansata\\lab5\\catalog.json");
        CatalogUtil.view(catalog.findById("article1"));
        System.out.println(catalog);
    }

}