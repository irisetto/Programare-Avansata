package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.forLanguageTag("en-US"));
        Locale locale = Locale.getDefault();
        ResourceBundle messages =
                ResourceBundle.getBundle("res.Messages", locale);

        while (true) {
            System.out.print(messages.getString("prompt"));
            String command = scanner.nextLine().trim();

            if (command.equals("exit")) {
                break;
            } else if (command.equals("locales")) {

                DisplayLocales.execute(locale);
            } else if (command.startsWith("locale.set")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    String localeTag = parts[1];
                    locale = Locale.forLanguageTag(localeTag);
                    messages = ResourceBundle.getBundle("res.Messages", locale);
                    SetLocale.execute(locale);
                } else {
                    System.out.print(messages.getString("invalid"));
                }
            } else if (command.startsWith("info")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    String localeTag = parts[1];
                    Info.execute(localeTag,locale);
                } else {
                    System.out.print(messages.getString("invalid"));
                }
            } else {
                System.out.print(messages.getString("invalid"));
            }
        }
        scanner.close();
    }
}
