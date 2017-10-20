package com.usc.logger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;


public class LogHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MyLogger.db";

    public LogHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + LogDbSchema.ActivityTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                LogDbSchema.ActivityTable.Cols.UUID + ", "+
                LogDbSchema.ActivityTable.Cols.TYPE + ", "+
                LogDbSchema.ActivityTable.Cols.DATE + ", "+
                LogDbSchema.ActivityTable.Cols.TITLE + ", "+
                LogDbSchema.ActivityTable.Cols.COMMENT + ", "+
                LogDbSchema.ActivityTable.Cols.DURATION + ", "+
                LogDbSchema.ActivityTable.Cols.LOCATION + ", "+
                LogDbSchema.ActivityTable.Cols.DESTINATION +
            ")"
        );

        db.execSQL("create table " + LogDbSchema.SettingsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                LogDbSchema.SettingsTable.Cols.ID + ", "+
                LogDbSchema.SettingsTable.Cols.UUID + ", "+
                LogDbSchema.SettingsTable.Cols.NAME + ", "+
                LogDbSchema.SettingsTable.Cols.EMAIL + ", "+
                LogDbSchema.SettingsTable.Cols.GENDER + ", "+
                LogDbSchema.SettingsTable.Cols.COMMENT +
                ")"
        );

        db.execSQL("insert into " + LogDbSchema.SettingsTable.NAME + "(" +
                LogDbSchema.SettingsTable.Cols.ID + ", "+
                LogDbSchema.SettingsTable.Cols.UUID + ", "+
                LogDbSchema.SettingsTable.Cols.NAME + ", "+
                LogDbSchema.SettingsTable.Cols.EMAIL + ", "+
                LogDbSchema.SettingsTable.Cols.GENDER + ", "+
                LogDbSchema.SettingsTable.Cols.COMMENT +
                ") values ('1105344','"+ UUID.randomUUID()+"','Binod Pariyar','B_P054@student.usc.edu.au','Male','No Comments')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
