package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public static void execute(Locale localeTag) {

        ResourceBundle messages = ResourceBundle.getBundle("res.Messages",localeTag);
        String currentLocale = messages.getString("locale.set").replace("{0}", localeTag.toString());
        System.out.println(currentLocale);
    }
}
