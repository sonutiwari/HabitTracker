package in.co.chicmic.habittracker.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import in.co.chicmic.habittracker.dbContract.HabitContract;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habitTracker.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_TRACKING_DIARY = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME +
                "(" + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitContract.HabitEntry.COLUMN_DATE + " INTEGER NOT NULL," +
                HabitContract.HabitEntry.COLUMN_HABIT + " INTEGER NOT NULL," +
                HabitContract.HabitEntry.COLUMN_COMMENT + " TEXT);";
        Log.v("Sonu", "create table: " + CREATE_TABLE_TRACKING_DIARY);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRACKING_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertHabit(int date, int habit, String comment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_DATE, date);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT, habit);
        values.put(HabitContract.HabitEntry.COLUMN_COMMENT, comment);
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor readHabits() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_DATE,
                HabitContract.HabitEntry.COLUMN_HABIT,
                HabitContract.HabitEntry.COLUMN_COMMENT
        };
        return db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
}
