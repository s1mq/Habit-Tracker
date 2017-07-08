package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity
        mDbHelper = new HabitDbHelper(this);

        // Insert three hardcoded habits data into the database
        insertHabits();
        // Display information about the data in the database in a log message
        displayDatabaseInfo();

    }

    // Helper method that takes information from the cursor and displays it in a Log String and then
    // closes the cursor to release its resources and make it invalid.
    private void displayDatabaseInfo() {
        Cursor cursor = read();
        cursor.close();
    }

    // Three helper methods to insert hardcoded habit data into the database
    private void insertHabits() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and brushing teeth habit's attributes are the values.
        ContentValues habitOne = new ContentValues();
        habitOne.put(HabitEntry.COLUMN_HABIT_NAME, "Brush teeth");
        habitOne.put(HabitEntry.COLUMN_HABIT_DONE, HabitEntry.DONE_NO);
        habitOne.put(HabitEntry.COLUMN_HABIT_TIME, 3);
        habitOne.put(HabitEntry.COLUMN_HABIT_MOOD, HabitEntry.MOOD_SAD);

        ContentValues habitTwo = new ContentValues();
        habitTwo.put(HabitEntry.COLUMN_HABIT_NAME, "Have breakfast");
        habitTwo.put(HabitEntry.COLUMN_HABIT_DONE, HabitEntry.DONE_YES);
        habitTwo.put(HabitEntry.COLUMN_HABIT_TIME, 30);
        habitTwo.put(HabitEntry.COLUMN_HABIT_MOOD, HabitEntry.MOOD_HAPPY);

        ContentValues habitThree = new ContentValues();
        habitThree.put(HabitEntry.COLUMN_HABIT_NAME, "Go Running");
        habitThree.put(HabitEntry.COLUMN_HABIT_DONE, HabitEntry.DONE_YES);
        habitThree.put(HabitEntry.COLUMN_HABIT_TIME, 60);
        habitThree.put(HabitEntry.COLUMN_HABIT_MOOD, HabitEntry.MOOD_HAPPY);

        // Insert a new row for habits in the database, returning the ID of that new row.
        // The first argument for db.insert() is the habits table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for the habit.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, habitOne);
        long newRowIdTwo = db.insert(HabitEntry.TABLE_NAME, null, habitTwo);
        long newRowIdThree = db.insert(HabitEntry.TABLE_NAME, null, habitThree);
    }

    /**
     * Helper method to read the data from the database, store it in a Cursor object
     * and return this Cursor
     */
    private Cursor read() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DONE,
                HabitEntry.COLUMN_HABIT_TIME,
                HabitEntry.COLUMN_HABIT_MOOD,
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,  // The table to query
                projection,             // The columnds to return
                null,                   // The columns fort the WHERE clause
                null,                   // The values for the WHERE clause
                null,                   // Don't group the rows
                null,                   // Don't filter by row groups
                null);                  // The sort order


        // Create a String to build upon with the while loop
        String logMessage = "These are the contents of my habits table. Currently there are " +
                cursor.getCount() + " habits:\n\n";
        logMessage = logMessage + HabitEntry._ID + " - " +
                HabitEntry.COLUMN_HABIT_NAME + " - " +
                HabitEntry.COLUMN_HABIT_DONE + " - " +
                HabitEntry.COLUMN_HABIT_TIME + " - " +
                HabitEntry.COLUMN_HABIT_MOOD + "\n";

        // Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int doneColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DONE);
        int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIME);
        int moodColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_MOOD);

        // Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentId = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            int currendDone = cursor.getInt(doneColumnIndex);
            int currentTime = cursor.getInt(timeColumnIndex);
            int currentMood = cursor.getInt(moodColumnIndex);
            // Display the values from each column of the current row in the String
            logMessage = logMessage + "\n" + currentId + " - " +
                    currentName + " - " +
                    currendDone + " - " +
                    currentTime + " - " +
                    currentMood;
        }
        // Display the log message containing the full String
        Log.v(LOG_TAG, logMessage);
        return cursor;
    }

    private void logMessage() {

    }
}
