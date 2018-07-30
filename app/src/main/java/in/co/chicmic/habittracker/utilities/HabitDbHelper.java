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

    public HabitDbHelper(Context pContext) {
        super(pContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pSqLiteDatabase) {
        String CREATE_TABLE_TRACKING_DIARY = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME +
                "(" + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitContract.HabitEntry.COLUMN_DATE + " INTEGER NOT NULL," +
                HabitContract.HabitEntry.COLUMN_HABIT + " INTEGER NOT NULL," +
                HabitContract.HabitEntry.COLUMN_COMMENT + " TEXT);";
        Log.v("Sonu", "create table: " + CREATE_TABLE_TRACKING_DIARY);
        pSqLiteDatabase.execSQL(CREATE_TABLE_TRACKING_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int pOldVersion, int pNewVersion) {

    }

    public void insertHabit(int pDate, int pHabit, String pComment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_DATE, pDate);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT, pHabit);
        values.put(HabitContract.HabitEntry.COLUMN_COMMENT, pComment);
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
