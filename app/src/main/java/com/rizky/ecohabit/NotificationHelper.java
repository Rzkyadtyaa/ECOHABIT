package com.rizky.ecohabit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    Context context;

    public NotificationHelper(Context context) {

        this.context = context;
    }

    // CREATE NOTIFICATION
    public void showNotification() {

        String channelId = "ECOHABIT_CHANNEL";

        // CHANNEL ANDROID 8+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,
                            "EcoHabit Reminder",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            NotificationManager manager =
                    context.getSystemService(
                            NotificationManager.class
                    );

            manager.createNotificationChannel(channel);
        }

        // NOTIFICATION
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        context,
                        channelId
                )
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("🌱 ECOHABIT")
                        .setContentText(
                                "Jangan lupa habit hari ini 😎🔥"
                        )
                        .setPriority(
                                NotificationCompat.PRIORITY_HIGH
                        )
                        .setAutoCancel(true);

        NotificationManagerCompat managerCompat =
                NotificationManagerCompat.from(context);

        managerCompat.notify(1, builder.build());
    }
}