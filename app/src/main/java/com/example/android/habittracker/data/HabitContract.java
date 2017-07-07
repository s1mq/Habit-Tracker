package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * API contract for the Habit Tracker app.
 */

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static abstract class HabitEntry implements BaseColumns {

        /** Name of the database table for habits */
        public static final String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the habit
         *
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_NAME = "name";

        /**
         * Done status for the habit.
         *
         * The only possible values are {@Link #DONE_NO} and {@Link #DONE_YES}.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_HABIT_DONE = "done";

        /**
         * Time spent on the habit (in minutes).
         *
         * Type: INTEGER
         */
        public static final String COLUMN_HABIT_TIME = "time";

        /**
         * Mood after completing the habit.
         *
         * The only possible values are {@Link #MOOD_INDIFFERENT}, {@Link #MOOD_HAPPY} or
         * {@Link #MOOD_SAD}.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_HABIT_MOOD = "mood";

        /**
         * Possible values for done status of the habit.
         */
        public static final int DONE_NO = 0;
        public static final int DONE_YES = 1;

        /**
         * Possible values for mood status of the habit.
         */
        public static final int MOOD_INDIFFERENT = 0;
        public static final int MOOD_HAPPY = 1;
        public static final int MOOD_SAD = 2;
    }
}
