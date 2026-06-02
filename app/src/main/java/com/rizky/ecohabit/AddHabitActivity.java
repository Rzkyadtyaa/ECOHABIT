package com.rizky.ecohabit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rizky.ecohabit.database.DatabaseHelper;

public class AddHabitActivity extends AppCompatActivity {

    EditText edtHabit;

    Button btnSave;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_habit);

        edtHabit =
                findViewById(R.id.edtHabit);

        btnSave =
                findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {

            String habit =
                    edtHabit.getText()
                            .toString()
                            .trim();

            // VALIDASI
            if(habit.isEmpty()) {

                Toast.makeText(
                        this,
                        "Habit tidak boleh kosong",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                // SIMPAN SQLITE
                db.insertHabit(habit);

                Toast.makeText(
                        this,
                        "🌱 Habit berhasil ditambahkan!",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });
    }
}