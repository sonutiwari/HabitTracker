package in.co.chicmic.habittracker.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.co.chicmic.habittracker.R;
import in.co.chicmic.habittracker.dbContract.HabitContract;
import in.co.chicmic.habittracker.utilities.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);
        Date date = new Date();
        SimpleDateFormat formatter
                = new SimpleDateFormat(getString(R.string.pattern), Locale.ENGLISH);
        String dateString = formatter.format(date);
        int dateInt = Integer.parseInt(dateString);

        habitDbHelper.insertHabit(dateInt, HabitContract.HabitEntry.HABIT_PROGRAMMING,
                getString(R.string.comment));
        habitDbHelper.insertHabit(dateInt, HabitContract.HabitEntry.HABIT_UDACITY,
                getString(R.string.comment));
        Cursor cursor = habitDbHelper.readHabits();
        while (cursor.moveToNext()) {
            Log.v(getString(R.string.tag)
                    , getString(R.string.message) + cursor.getInt(0)
                            + getString(R.string.space) + cursor.getInt(1)
                            + getString(R.string.space) + cursor.getInt(2)
                            + getString(R.string.space) + cursor.getString(3));
        }
    }


}
