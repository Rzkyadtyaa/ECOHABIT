package com.rizky.ecohabit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    Button btnStart;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onboarding);

        btnStart =
                findViewById(R.id.btnStart);

        sharedPreferences =
                getSharedPreferences(
                        "ECOHABIT",
                        MODE_PRIVATE
                );

        btnStart.setOnClickListener(v -> {

            boolean isLogin =
                    sharedPreferences.getBoolean(
                            "isLogin",
                            false
                    );

            if (isLogin) {

                startActivity(
                        new Intent(
                                OnboardingActivity.this,
                                MainActivity.class
                        )
                );

            } else {

                startActivity(
                        new Intent(
                                OnboardingActivity.this,
                                LoginActivity.class
                        )
                );
            }

            finish();
        });
    }
}