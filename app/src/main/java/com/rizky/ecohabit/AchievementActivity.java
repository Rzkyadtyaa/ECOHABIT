package com.rizky.ecohabit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rizky.ecohabit.database.DatabaseHelper;

public class AchievementActivity extends AppCompatActivity {

    CardView cardFirst;
    CardView cardWarrior;
    CardView cardLegend;

    TextView txtFirst;
    TextView txtWarrior;
    TextView txtLegend;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_achievement);

        // INIT VIEW

        cardFirst =
                findViewById(R.id.cardFirst);

        cardWarrior =
                findViewById(R.id.cardWarrior);

        cardLegend =
                findViewById(R.id.cardLegend);

        txtFirst =
                findViewById(R.id.txtFirst);

        txtWarrior =
                findViewById(R.id.txtWarrior);

        txtLegend =
                findViewById(R.id.txtLegend);

        // DATABASE

        databaseHelper =
                new DatabaseHelper(this);

        // TOTAL POINT

        int totalPoint =
                databaseHelper.getTotalPoints();

        // FIRST HABIT

        if(totalPoint >= 10) {

            txtFirst.setText("UNLOCKED");

            txtFirst.setTextColor(
                    getResources().getColor(
                            android.R.color.holo_green_dark
                    )
            );

            cardFirst.setCardBackgroundColor(
                    getResources().getColor(
                            android.R.color.holo_green_light
                    )
            );
        }

        // GREEN WARRIOR

        if(totalPoint >= 150) {

            txtWarrior.setText("UNLOCKED");

            txtWarrior.setTextColor(
                    getResources().getColor(
                            android.R.color.holo_green_dark
                    )
            );

            cardWarrior.setCardBackgroundColor(
                    getResources().getColor(
                            android.R.color.holo_green_light
                    )
            );
        }

        // ECO LEGEND

        if(totalPoint >= 500) {

            txtLegend.setText("UNLOCKED");

            txtLegend.setTextColor(
                    getResources().getColor(
                            android.R.color.holo_green_dark
                    )
            );

            cardLegend.setCardBackgroundColor(
                    getResources().getColor(
                            android.R.color.holo_green_light
                    )
            );
        }
    }
}