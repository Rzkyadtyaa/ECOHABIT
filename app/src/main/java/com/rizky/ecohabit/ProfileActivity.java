package com.rizky.ecohabit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rizky.ecohabit.database.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {

    TextView txtAvatar;
    TextView txtLevel;
    TextView txtPoint;
    TextView txtBadge;

    EditText edtName;

    ProgressBar progressLevel;

    Button btnPanda;
    Button btnFox;
    Button btnCat;
    Button btnFrog;
    Button btnSaveName;

    DatabaseHelper db;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        // SHARED PREFERENCES
        preferences =
                getSharedPreferences(
                        "ECOHABIT",
                        MODE_PRIVATE
                );

        // INIT VIEW

        txtAvatar =
                findViewById(R.id.txtAvatar);

        txtLevel =
                findViewById(R.id.txtLevel);

        txtPoint =
                findViewById(R.id.txtPoint);

        txtBadge =
                findViewById(R.id.txtBadge);

        edtName =
                findViewById(R.id.edtName);

        progressLevel =
                findViewById(R.id.progressLevel);

        btnPanda =
                findViewById(R.id.btnPanda);

        btnFox =
                findViewById(R.id.btnFox);

        btnCat =
                findViewById(R.id.btnCat);

        btnFrog =
                findViewById(R.id.btnFrog);

        btnSaveName =
                findViewById(R.id.btnSaveName);

        db = new DatabaseHelper(this);

        // LOAD USERNAME
        String savedName =
                preferences.getString(
                        "username",
                        "Eco User"
                );

        edtName.setText(savedName);

        // UPDATE PROFILE
        updateProfile();

        // SAVE USERNAME
        btnSaveName.setOnClickListener(v -> {

            String name =
                    edtName.getText()
                            .toString();

            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putString(
                    "username",
                    name
            );

            editor.apply();

            Toast.makeText(
                    this,
                    "Nama berhasil disimpan 😎",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // AVATAR BUTTON
        btnPanda.setOnClickListener(v -> {
            txtAvatar.setText("🐼");
        });

        btnFox.setOnClickListener(v -> {
            txtAvatar.setText("🦊");
        });

        btnCat.setOnClickListener(v -> {
            txtAvatar.setText("🐱");
        });

        btnFrog.setOnClickListener(v -> {
            txtAvatar.setText("🐸");
        });
    }

    // UPDATE PROFILE
    private void updateProfile() {

        int totalPoint =
                db.getTotalPoints();

        txtPoint.setText(
                "⭐ " + totalPoint + " Point"
        );

        // LEVEL SYSTEM
        if(totalPoint >= 200) {

            txtLevel.setText(
                    "🏆 Eco Master"
            );

            txtBadge.setText(
                    "🏅 Earth Protector"
            );

            progressLevel.setProgress(100);

        }
        else if(totalPoint >= 100) {

            txtLevel.setText(
                    "🌿 Eco Warrior"
            );

            txtBadge.setText(
                    "🏅 Green Warrior"
            );

            progressLevel.setProgress(70);

        }
        else {

            txtLevel.setText(
                    "🌱 Eco Beginner"
            );

            txtBadge.setText(
                    "🏅 Earth Beginner"
            );

            progressLevel.setProgress(40);
        }
    }
}