package org.example;

public class Programmer extends Person {
    private String usedProgrammingLanguage;

    public Programmer(String name, String birthDate, String usedProgrammingLanguage) {
        super(name, birthDate);
        this.usedProgrammingLanguage = usedProgrammingLanguage;
    }

    public String getUsedProgrammingLanguage() {
        return usedProgrammingLanguage;
    }

    public void setUsedProgrammingLanguage(String usedProgrammingLanguage) {
        this.usedProgrammingLanguage = usedProgrammingLanguage;
    }

    @Override
    public String toString() {
        return "Programmer: " + "name='" + this.getName() + '\'' +
                " | " + "birthDate='" + this.getBirthDate() + '\'' +
                " | " + "usedProgrammingLanguage='" + usedProgrammingLanguage + '\'' + " | "
                + "numberOfRelationships= " + this.getRelationships().size() + '\n';
    }
}
