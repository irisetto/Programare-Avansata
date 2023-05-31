package org.example.com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void execute(String localeTag,Locale currentLocale) {
        Locale locale = Locale.forLanguageTag(localeTag);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locale);
        ResourceBundle localeSensitive = ResourceBundle.getBundle("res.Messages", currentLocale);

        String info = localeSensitive.getString("info").replace("{0}", localeTag.toString());
        System.out.println(info);
        String country = locale.getDisplayCountry();
        String language = locale.getDisplayLanguage();

        System.out.println(localeSensitive.getString("countryText")+ messages.getString("country") );
        System.out.println(localeSensitive.getString("language")+ language);
        String currencySymbol;
        if (localeTag.equals("ro") ){
            currencySymbol = "RON";
        } else {
            Currency currency = Currency.getInstance(locale);
            currencySymbol = currency.getSymbol(locale);
        }
        System.out.println(localeSensitive.getString("currency") + currencySymbol);

        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        System.out.println(localeSensitive.getString("week") + String.join(", ", dateFormatSymbols.getWeekdays()));
        System.out.println(localeSensitive.getString("months") + String.join(", ", dateFormatSymbols.getMonths()));

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(new Date());
        System.out.println(localeSensitive.getString("today") + formattedDate);
    }
}