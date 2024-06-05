package com.example.pruebas_tfg.LocaleHelper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    // Método para obtener el idioma guardado
    private String getSavedLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "es");
    }

    // Método para guardar el idioma seleccionado
    private void saveLanguage(Context context, String lang) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    // Método para cambiar la configuración regional
    private void changeLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        saveLanguage(context, lang);
    }
}