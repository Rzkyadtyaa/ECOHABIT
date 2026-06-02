package com.rizky.ecohabit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizky.ecohabit.adapter.HabitAdapter;
import com.rizky.ecohabit.database.DatabaseHelper;
import com.rizky.ecohabit.model.Habit;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // ================= RECYCLER =================

    RecyclerView recyclerView;

    // ================= BUTTON =================

    FloatingActionButton fabAdd;

    Button btnStats;
    Button btnTips;
    Button btnCalendar;
    Button btnAchievement;
    Button btnProfile;

    Button btnStatsBottom;
    Button btnTipsBottom;

    ImageButton btnSettings;

    // ================= TEXT =================

    TextView txtDashboardPoint;
    TextView txtProgress;
    TextView txtLevel;
    TextView txtStreak;
    TextView txtChallenge;

    // ================= PROGRESS =================

    ProgressBar progressHabit;

    // ================= DATABASE =================

    DatabaseHelper db;

    // ================= ARRAYLIST =================

    ArrayList<Habit> list;

    HabitAdapter adapter;

    int completedHabit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ================= NOTIFICATION =================

        NotificationHelper notificationHelper =
                new NotificationHelper(this);

        notificationHelper.showNotification();

        // ================= INIT VIEW =================

        recyclerView =
                findViewById(R.id.recyclerHabit);

        fabAdd =
                findViewById(R.id.fabAdd);

        btnStats =
                findViewById(R.id.btnStats);

        btnTips =
                findViewById(R.id.btnTips);

        btnCalendar =
                findViewById(R.id.btnCalendar);

        btnAchievement =
                findViewById(R.id.btnAchievement);

        btnProfile =
                findViewById(R.id.btnProfile);

        btnStatsBottom =
                findViewById(R.id.btnStatsBottom);

        btnTipsBottom =
                findViewById(R.id.btnTipsBottom);

        btnSettings =
                findViewById(R.id.btnSettings);

        txtDashboardPoint =
                findViewById(R.id.txtDashboardPoint);

        txtProgress =
                findViewById(R.id.txtProgress);

        txtLevel =
                findViewById(R.id.txtLevel);

        txtStreak =
                findViewById(R.id.txtStreak);

        txtChallenge =
                findViewById(R.id.txtChallenge);

        progressHabit =
                findViewById(R.id.progressHabit);

        // ================= DATABASE =================

        db = new DatabaseHelper(this);

        // ================= ARRAYLIST =================

        list = new ArrayList<>();

        // ================= LOAD DATA =================

        loadData();

        // ================= RECYCLER VIEW =================

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        adapter = new HabitAdapter(
                this,
                list
        );

        recyclerView.setAdapter(adapter);

        // ================= UPDATE DASHBOARD =================

        updateDashboard();

        // ================= LOAD CHALLENGE =================

        loadChallenge();

        // ================= ADD HABIT =================

        fabAdd.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AddHabitActivity.class
                    );

            startActivity(intent);
        });

        // ================= STATISTICS =================

        btnStats.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            StatisticsActivity.class
                    );

            startActivity(intent);
        });

        // ================= TIPS =================

        btnTips.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            TipsActivity.class
                    );

            startActivity(intent);
        });

        // ================= CALENDAR =================

        btnCalendar.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            CalendarActivity.class
                    );

            startActivity(intent);
        });

        // ================= ACHIEVEMENT =================

        btnAchievement.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AchievementActivity.class
                    );

            startActivity(intent);
        });

        // ================= PROFILE =================

        btnProfile.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ProfileActivity.class
                    );

            startActivity(intent);
        });

        // ================= SETTINGS =================

        btnSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);
        });

        // ================= BOTTOM STATS =================

        btnStatsBottom.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            StatisticsActivity.class
                    );

            startActivity(intent);
        });

        // ================= BOTTOM TIPS =================

        btnTipsBottom.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            TipsActivity.class
                    );

            startActivity(intent);
        });
    }

    // ================= LOAD SQLITE DATA =================

    private void loadData() {

        list.clear();

        completedHabit = 0;

        Cursor c = db.getHabits();

        while (c.moveToNext()) {

            Habit habit = new Habit(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getInt(3)
            );

            list.add(habit);

            // ================= COMPLETED HABIT =================

            if (habit.getStatus() == 1) {

                completedHabit++;
            }
        }

        c.close();
    }

    // ================= UPDATE DASHBOARD =================

    private void updateDashboard() {

        int totalHabit =
                list.size();

        int totalPoint =
                db.getTotalPoints();

        // ================= POINT =================

        txtDashboardPoint.setText(
                totalPoint + " Points"
        );

        // ================= PROGRESS =================

        int progress = 0;

        if (totalHabit > 0) {

            progress =
                    (completedHabit * 100)
                            / totalHabit;
        }

        progressHabit.setProgress(progress);

        txtProgress.setText(
                progress + "% Completed"
        );

        // ================= LEVEL SYSTEM =================

        if (totalPoint >= 500) {

            txtLevel.setText(
                    "🌍 Eco Legend"
            );

        } else if (totalPoint >= 200) {

            txtLevel.setText(
                    "🏆 Eco Master"
            );

        } else if (totalPoint >= 100) {

            txtLevel.setText(
                    "🌿 Eco Warrior"
            );

        } else {

            txtLevel.setText(
                    "🌱 Eco Beginner"
            );
        }

        // ================= STREAK =================

        txtStreak.setText(
                completedHabit + " Habit Completed "
        );
    }

    // ================= DAILY CHALLENGE =================

    private void loadChallenge() {

        String[] challenges = {

                "🌍 Tidak memakai sedotan plastik hari ini (+20)",

                "💧 Hemat air saat mandi (+15)",

                "♻️ Gunakan botol reusable (+20)",

                "🚶 Jalan kaki minimal 500 langkah (+10)",

                "🌱 Menanam 1 tanaman kecil (+30)",

                "🛍️ Gunakan tas belanja reusable (+15)"
        };

        Random random = new Random();

        int index =
                random.nextInt(challenges.length);

        txtChallenge.setText(
                challenges[index]
        );
    }

    @Override
    protected void onResume() {

        super.onResume();

        loadData();

        adapter.notifyDataSetChanged();

        updateDashboard();

        loadChallenge();
    }
}