package org.example;

public class Designer extends Person {
    private String usedDesignApp;

    public Designer(String name, String birthDate, String usedDesignApp) {
        super(name, birthDate);
        this.usedDesignApp = usedDesignApp;
    }

    public String getUsedDesignApp() {
        return usedDesignApp;
    }

    public void setUsedDesignApp(String usedDesignApp) {
        this.usedDesignApp = usedDesignApp;
    }

    @Override
    public String toString() {
        return "Designer: " + "name='" + this.getName() + '\'' +
                " | " + "birthDate='" + this.getBirthDate() + '\'' +
                " | " + "usedDesignApp='" + usedDesignApp + '\'' + " | "
                + "numberOfRelationships= " + this.getRelationships().size() + '\n';
    }
}
