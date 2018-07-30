package in.co.chicmic.habittracker.dbContract;

import android.provider.BaseColumns;


public class HabitContract {

    public HabitContract() {
    }

    public class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "tracking_diary";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_HABIT = "habit";
        public final static String COLUMN_COMMENT = "comment";

        public final static int HABIT_UDACITY = 0;
        public final static int HABIT_PROGRAMMING = 1;
    }

}
