package com.rizky.ecohabit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;

    Button btnLogin;

    TextView txtRegister;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // ================= INIT VIEW =================

        etUsername =
                findViewById(R.id.etUsername);

        etPassword =
                findViewById(R.id.etPassword);

        btnLogin =
                findViewById(R.id.btnLogin);

        txtRegister =
                findViewById(R.id.txtRegister);

        // ================= SHARED PREF =================

        sharedPreferences =
                getSharedPreferences(
                        "ECOHABIT",
                        MODE_PRIVATE
                );

        // ================= LOGIN BUTTON =================

        btnLogin.setOnClickListener(v -> {

            String username =
                    etUsername.getText()
                            .toString()
                            .trim();

            String password =
                    etPassword.getText()
                            .toString()
                            .trim();

            // VALIDASI

            if (username.isEmpty()
                    || password.isEmpty()) {

                Toast.makeText(
                        LoginActivity.this,
                        "Isi semua data terlebih dahulu",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // SIMPAN LOGIN

            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putString(
                    "username",
                    username
            );

            editor.putBoolean(
                    "isLogin",
                    true
            );

            editor.apply();

            Toast.makeText(
                    LoginActivity.this,
                    "Login Berhasil 🌱",
                    Toast.LENGTH_SHORT
            ).show();

            // PINDAH KE DASHBOARD

            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            MainActivity.class
                    );

            startActivity(intent);

            finish();
        });

        // ================= REGISTER =================

        txtRegister.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            RegisterActivity.class
                    );

            startActivity(intent);
        });
    }
}