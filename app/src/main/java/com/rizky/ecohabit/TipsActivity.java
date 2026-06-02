package com.rizky.ecohabit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TipsActivity extends AppCompatActivity {

    CardView cardWater;
    CardView cardPlastic;
    CardView cardBike;
    CardView cardElectric;
    CardView cardTree;
    CardView cardFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tips);

        // INIT CARD

        cardWater =
                findViewById(R.id.cardWater);

        cardPlastic =
                findViewById(R.id.cardPlastic);

        cardBike =
                findViewById(R.id.cardBike);

        cardElectric =
                findViewById(R.id.cardElectric);

        cardTree =
                findViewById(R.id.cardTree);

        cardFood =
                findViewById(R.id.cardFood);

        // WATER

        cardWater.setOnClickListener(v -> {

            openDetail(
                    "Hemat Air",
                    "💧"
            );

        });

        // PLASTIC

        cardPlastic.setOnClickListener(v -> {

            openDetail(
                    "Kurangi Plastik",
                    "♻"
            );

        });

        // BIKE

        cardBike.setOnClickListener(v -> {

            openDetail(
                    "Gunakan Sepeda",
                    "🚴"
            );

        });

        // ELECTRIC

        cardElectric.setOnClickListener(v -> {

            openDetail(
                    "Hemat Listrik",
                    "💡"
            );

        });

        // TREE

        cardTree.setOnClickListener(v -> {

            openDetail(
                    "Menanam Pohon",
                    "🌳"
            );

        });

        // FOOD

        cardFood.setOnClickListener(v -> {

            openDetail(
                    "Kurangi Food Waste",
                    "🍱"
            );

        });
    }

    // OPEN DETAIL

    private void openDetail(
            String title,
            String emoji
    ) {

        Intent intent =
                new Intent(
                        TipsActivity.this,
                        TipsDetailActivity.class
                );

        intent.putExtra(
                "title",
                title
        );

        intent.putExtra(
                "emoji",
                emoji
        );

        startActivity(intent);
    }
}