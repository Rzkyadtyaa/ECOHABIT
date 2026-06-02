package com.rizky.ecohabit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ecohabit.db";

    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "habit";

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE habit (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT," +
                        "status INTEGER," +
                        "points INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

    }

    // tambah habit
    public void insertHabit(String title) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues cv =
                new ContentValues();

        cv.put("title", title);
        cv.put("status", 0);
        cv.put("points", 10);

        db.insert(TABLE_NAME, null, cv);
    }

    // ambil semua data
    public Cursor getHabits() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM habit",
                null
        );
    }

    // update checklist
    public void updateStatus(int id,
                             int status) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues cv =
                new ContentValues();

        cv.put("status", status);

        db.update(
                TABLE_NAME,
                cv,
                "id=?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    // hapus habit
    public void deleteHabit(int id) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                "id=?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    // total point
    public int getTotalPoints() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT SUM(points) FROM habit WHERE status=1",
                null
        );

        int total = 0;

        if(c.moveToFirst()) {

            total = c.getInt(0);
        }

        return total;
    }
}