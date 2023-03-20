package org.example;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Document {
    private String id;
    private String title;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document() {
    }

    public Document(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location; //exceptie path invalid
        try{
            Paths.get(location);
        }
        catch (InvalidPathException ex) {
            System.err.println("The path " + location + " is not missing!");
        }
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getTags() {
        return tags;
    }


    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '\n';
    }
}
