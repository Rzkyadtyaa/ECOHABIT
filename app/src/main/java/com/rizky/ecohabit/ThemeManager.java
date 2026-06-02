package com.rizky.ecohabit;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {

    private static final String PREF_NAME =
            "theme_pref";

    private static final String KEY_DARK =
            "dark_mode";

    // SAVE THEME
    public static void setDarkMode(
            Context context,
            boolean isDark
    ) {

        SharedPreferences preferences =
                context.getSharedPreferences(
                        PREF_NAME,
                        Context.MODE_PRIVATE
                );

        SharedPreferences.Editor editor =
                preferences.edit();

        editor.putBoolean(
                KEY_DARK,
                isDark
        );

        editor.apply();

        // APPLY THEME

        if(isDark) {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
            );

        }
        else {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
            );
        }
    }

    // LOAD THEME
    public static void loadTheme(
            Context context
    ) {

        SharedPreferences preferences =
                context.getSharedPreferences(
                        PREF_NAME,
                        Context.MODE_PRIVATE
                );

        boolean isDark =
                preferences.getBoolean(
                        KEY_DARK,
                        false
                );

        if(isDark) {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
            );

        }
        else {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
            );
        }
    }
}