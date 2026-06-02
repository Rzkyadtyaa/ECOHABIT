package com.rizky.ecohabit;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rizky.ecohabit.database.DatabaseHelper;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    TextView txtPoint;
    TextView txtLevel;
    TextView txtStreak;

    ProgressBar progressEco;

    BarChart barChart;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        // INIT VIEW

        txtPoint =
                findViewById(R.id.txtPoint);

        txtLevel =
                findViewById(R.id.txtLevel);

        txtStreak =
                findViewById(R.id.txtStreak);

        progressEco =
                findViewById(R.id.progressEco);

        barChart =
                findViewById(R.id.barChart);

        // DATABASE

        databaseHelper =
                new DatabaseHelper(this);

        // TOTAL POINT

        int totalPoint =
                databaseHelper.getTotalPoints();

        txtPoint.setText(
                String.valueOf(totalPoint)
        );

        // PROGRESS

        int progress =
                totalPoint % 100;

        progressEco.setProgress(progress);

        // LEVEL SYSTEM

        String level;

        if(totalPoint >= 500) {

            level = "🌍 Eco Legend";

        }
        else if(totalPoint >= 300) {

            level = "🏆 Eco Master";

        }
        else if(totalPoint >= 150) {

            level = "🌱 Green Warrior";

        }
        else {

            level = "✨ Eco Beginner";
        }

        txtLevel.setText(level);

        // STREAK

        int streak =
                totalPoint / 10;

        txtStreak.setText(
                streak + " Days"
        );

        // CHART

        setupChart(totalPoint);
    }

    // BAR CHART
    private void setupChart(int totalPoint) {

        ArrayList<BarEntry> entries =
                new ArrayList<>();

        entries.add(new BarEntry(1, totalPoint / 4f));
        entries.add(new BarEntry(2, totalPoint / 2f));
        entries.add(new BarEntry(3, totalPoint / 1.5f));
        entries.add(new BarEntry(4, totalPoint));

        BarDataSet dataSet =
                new BarDataSet(
                        entries,
                        "Eco Analytics"
                );

        dataSet.setColors(
                ColorTemplate.MATERIAL_COLORS
        );

        dataSet.setValueTextSize(14f);

        dataSet.setValueTextColor(
                Color.BLACK
        );

        BarData barData =
                new BarData(dataSet);

        barChart.setData(barData);

        Description description =
                new Description();

        description.setText(
                "Weekly Eco Progress"
        );

        barChart.setDescription(description);

        barChart.animateY(1500);

        barChart.invalidate();
    }
}