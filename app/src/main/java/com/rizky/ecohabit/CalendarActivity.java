package com.rizky.ecohabit;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rizky.ecohabit.database.DatabaseHelper;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;

    TextView txtCalendarStreak;
    TextView txtHistory;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        // INIT VIEW

        calendarView =
                findViewById(R.id.calendarView);

        txtCalendarStreak =
                findViewById(R.id.txtCalendarStreak);

        txtHistory =
                findViewById(R.id.txtHistory);

        // DATABASE

        databaseHelper =
                new DatabaseHelper(this);

        // TOTAL POINT

        int totalPoint =
                databaseHelper.getTotalPoints();

        // STREAK

        int streak =
                totalPoint / 10;

        txtCalendarStreak.setText(
                streak + " Days"
        );

        // LOAD HISTORY

        loadHistory();

        // CALENDAR CLICK

        calendarView.setOnDateChangeListener(
                (view, year, month, dayOfMonth) -> {

                    String selectedDate =
                            dayOfMonth + "/"
                                    + (month + 1)
                                    + "/"
                                    + year;

                    Toast.makeText(
                            CalendarActivity.this,
                            "📅 " + selectedDate,
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );
    }

    // LOAD HISTORY
    private void loadHistory() {

        Cursor c =
                databaseHelper.getHabits();

        StringBuilder history =
                new StringBuilder();

        while(c.moveToNext()) {

            String title =
                    c.getString(1);

            int status =
                    c.getInt(2);

            if(status == 1) {

                history.append("✅ ")
                        .append(title)
                        .append("\n");

            }
            else {

                history.append("⬜ ")
                        .append(title)
                        .append("\n");
            }
        }

        c.close();

        // EMPTY CHECK

        if(history.length() == 0) {

            txtHistory.setText(
                    "No activity yet 😴"
            );

        }
        else {

            txtHistory.setText(
                    history.toString()
            );
        }
    }
}