package com.rizky.ecohabit;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rizky.ecohabit.database.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity {

    Switch switchDark;
    Switch switchNotification;

    Button btnReset;

    DatabaseHelper databaseHelper;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // LOAD THEME

        ThemeManager.loadTheme(this);

        setContentView(R.layout.activity_settings);

        // ================= INIT VIEW =================

        switchDark =
                findViewById(R.id.switchDark);

        switchNotification =
                findViewById(R.id.switchNotification);

        btnReset =
                findViewById(R.id.btnReset);

        // ================= DATABASE =================

        databaseHelper =
                new DatabaseHelper(this);

        // ================= SETTINGS PREF =================

        preferences =
                getSharedPreferences(
                        "settings",
                        MODE_PRIVATE
                );

        // ================= LOAD DARK MODE =================

        boolean darkMode =
                preferences.getBoolean(
                        "dark_mode",
                        false
                );

        switchDark.setChecked(darkMode);

        // ================= LOAD NOTIFICATION =================

        boolean notification =
                preferences.getBoolean(
                        "notification",
                        true
                );

        switchNotification.setChecked(notification);

        // ================= DARK MODE =================

        switchDark.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    ThemeManager.setDarkMode(
                            SettingsActivity.this,
                            isChecked
                    );

                    preferences.edit()
                            .putBoolean(
                                    "dark_mode",
                                    isChecked
                            )
                            .apply();

                    recreate();

                    Toast.makeText(
                            SettingsActivity.this,
                            "🌙 Theme Updated",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        // ================= NOTIFICATION =================

        switchNotification.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    preferences.edit()
                            .putBoolean(
                                    "notification",
                                    isChecked
                            )
                            .apply();

                    Toast.makeText(
                            SettingsActivity.this,
                            isChecked
                                    ? "🔔 Notification ON"
                                    : "🔕 Notification OFF",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        // ================= RESET DATA =================

        btnReset.setOnClickListener(v -> {

            new AlertDialog.Builder(
                    SettingsActivity.this
            )

                    .setTitle("Reset Data")

                    .setMessage(
                            "Delete all ECOHABIT data and logout?"
                    )

                    .setPositiveButton(
                            "YES",
                            (dialog, which) -> {

                                // HAPUS DATABASE

                                deleteDatabase(
                                        "ecohabit.db"
                                );

                                // HAPUS LOGIN

                                getSharedPreferences(
                                        "ECOHABIT",
                                        MODE_PRIVATE
                                )
                                        .edit()
                                        .clear()
                                        .apply();

                                Toast.makeText(
                                        SettingsActivity.this,
                                        "🗑️ Data Deleted Successfully",
                                        Toast.LENGTH_LONG
                                ).show();

                                // KEMBALI KE SPLASH

                                Intent intent =
                                        new Intent(
                                                SettingsActivity.this,
                                                SplashActivity.class
                                        );

                                intent.addFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                );

                                startActivity(intent);

                                finish();
                            }
                    )

                    .setNegativeButton(
                            "CANCEL",
                            null
                    )

                    .show();
        });
    }
}