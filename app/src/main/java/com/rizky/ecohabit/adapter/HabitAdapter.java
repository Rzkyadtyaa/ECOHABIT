package com.rizky.ecohabit.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rizky.ecohabit.R;
import com.rizky.ecohabit.database.DatabaseHelper;
import com.rizky.ecohabit.model.Habit;

import java.util.ArrayList;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {

    Context context;

    ArrayList<Habit> list;

    DatabaseHelper db;

    public HabitAdapter(
            Context context,
            ArrayList<Habit> list
    ) {

        this.context = context;

        this.list = list;

        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(context)
                        .inflate(
                                R.layout.item_habit,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Habit habit =
                list.get(position);

        holder.txtHabit.setText(
                habit.getTitle()
        );

        holder.checkBox.setChecked(
                habit.getStatus() == 1
        );

        // CHECKLIST
        holder.checkBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    // UPDATE STATUS
                    db.updateStatus(
                            habit.getId(),
                            isChecked ? 1 : 0
                    );

                    // VIBRATION
                    Vibrator vibrator =
                            (Vibrator)
                                    context.getSystemService(
                                            Context.VIBRATOR_SERVICE
                                    );

                    if(vibrator != null) {

                        if(android.os.Build.VERSION.SDK_INT
                                >= android.os.Build.VERSION_CODES.O) {

                            vibrator.vibrate(
                                    VibrationEffect.createOneShot(
                                            100,
                                            VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                            );

                        }
                        else {

                            vibrator.vibrate(100);
                        }
                    }

                    // SOUND EFFECT
                    MediaPlayer mediaPlayer =
                            MediaPlayer.create(
                                    context,
                                    android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
                            );

                    mediaPlayer.start();
                }
        );

        // DELETE HABIT
        holder.itemView.setOnLongClickListener(v -> {

            db.deleteHabit(
                    habit.getId()
            );

            list.remove(position);

            notifyDataSetChanged();

            return true;
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtHabit;

        CheckBox checkBox;

        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            txtHabit =
                    itemView.findViewById(
                            R.id.txtHabit
                    );

            checkBox =
                    itemView.findViewById(
                            R.id.checkHabit
                    );
        }
    }
}