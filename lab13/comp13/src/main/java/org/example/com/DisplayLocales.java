package org.example.com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void execute(Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages",locale);

        String available = messages.getString("locales");
        System.out.println(available);
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale localee : locales) {
            System.out.println(localee.toString() + ": " + localee.getDisplayName());
        }
    }
}
