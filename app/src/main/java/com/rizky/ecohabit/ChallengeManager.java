package com.rizky.ecohabit;

import java.util.Random;

public class ChallengeManager {

    String[] challenges = {

            "🌍 Avoid Plastic Straw",
            "💧 Save Water Today",
            "🚶 Walk Instead of Vehicle",
            "🌱 Plant a Small Tree",
            "🔌 Turn Off Unused Lamp",
            "♻️ Recycle Plastic Waste",
            "🛍️ Bring Eco Bag",
            "🚰 Drink Without Plastic Bottle"
    };

    int[] rewards = {

            20,
            15,
            25,
            30,
            10,
            20,
            15,
            25
    };

    // RANDOM CHALLENGE

    public String getRandomChallenge() {

        Random random =
                new Random();

        int index =
                random.nextInt(
                        challenges.length
                );

        return challenges[index]
                + "\n🎁 Reward: "
                + rewards[index]
                + " Point";
    }
}