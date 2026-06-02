package com.rizky.ecohabit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etEmail;
    EditText etPassword;

    Button btnRegister;

    TextView txtLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        // INIT VIEW

        etUsername =
                findViewById(R.id.etUsername);

        etEmail =
                findViewById(R.id.etEmail);

        etPassword =
                findViewById(R.id.etPassword);

        btnRegister =
                findViewById(R.id.btnRegister);

        txtLogin =
                findViewById(R.id.txtLogin);

        // SHARED PREF
        sharedPreferences =
                getSharedPreferences(
                        "ECOHABIT",
                        MODE_PRIVATE
                );

        // REGISTER BUTTON
        btnRegister.setOnClickListener(v -> {

            String username =
                    etUsername.getText().toString();

            String email =
                    etEmail.getText().toString();

            String password =
                    etPassword.getText().toString();

            // VALIDASI
            if(username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()) {

                Toast.makeText(
                        this,
                        "Isi semua data",
                        Toast.LENGTH_SHORT
                ).show();

            }
            else {

                // SAVE ACCOUNT
                SharedPreferences.Editor editor =
                        sharedPreferences.edit();

                editor.putString(
                        "username",
                        username
                );

                editor.putString(
                        "email",
                        email
                );

                editor.putString(
                        "password",
                        password
                );

                editor.apply();

                Toast.makeText(
                        this,
                        "Register Berhasil 🚀",
                        Toast.LENGTH_SHORT
                ).show();

                // PINDAH LOGIN
                startActivity(
                        new Intent(
                                RegisterActivity.this,
                                LoginActivity.class
                        )
                );

                finish();
            }
        });

        // LOGIN CLICK
        txtLogin.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            RegisterActivity.this,
                            LoginActivity.class
                    )
            );

            finish();
        });
    }
}