package com.usc.logger.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.logger.database.LogDbSchema;
import com.usc.logger.cursors.LogCursorWrapper;
import com.usc.logger.database.LogHelper;
import com.usc.logger.models.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogLab {

    private static LogLab sMyActivitiesLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Log> mMyActivities;

    public static LogLab get(Context context){
        if(sMyActivitiesLab == null){
            sMyActivitiesLab = new LogLab(context);
        }
        return sMyActivitiesLab;
    }

    private LogLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new LogHelper(mContext).getWritableDatabase();

    }

    public Log addActivity(Log t){
        ContentValues values = getContentValues(t);

        mDatabase.insert(LogDbSchema.ActivityTable.NAME,null,values);
        return t;
    }

    public List<Log> getActivities(){
        List<Log> activities = new ArrayList<>();
        LogCursorWrapper cursor = queryActivity(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                activities.add(cursor.getActivity());
                cursor.moveToNext();
            }
        } finally {

        }

        return activities;

    }

    public Log getActivities(UUID id){

        LogCursorWrapper cursor = queryActivity(LogDbSchema.ActivityTable.Cols.UUID + " =?",new String[]{ id.toString() });

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getActivity();

        } finally {
            cursor.close();
        }

    }

    public void updateLog(Log t){

        String uuidString = t.getId().toString();
        ContentValues values = getContentValues(t);

        mDatabase.update(LogDbSchema.ActivityTable.NAME, values, LogDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    public void deleteLog(Log t){
        String uuidString = t.getId().toString();
        mDatabase.delete(LogDbSchema.ActivityTable.NAME, LogDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(Log activities){

        ContentValues values = new ContentValues();
        values.put(LogDbSchema.ActivityTable.Cols.UUID,activities.getId().toString());
        values.put(LogDbSchema.ActivityTable.Cols.TITLE,activities.getTitle());
        values.put(LogDbSchema.ActivityTable.Cols.COMMENT,activities.getComment());
        values.put(LogDbSchema.ActivityTable.Cols.DURATION,activities.getDuration());
        values.put(LogDbSchema.ActivityTable.Cols.LOCATION,activities.getLocation());
        values.put(LogDbSchema.ActivityTable.Cols.TYPE,activities.getType());
        values.put(LogDbSchema.ActivityTable.Cols.DATE,activities.getDate());
        values.put(LogDbSchema.ActivityTable.Cols.DESTINATION,activities.getDestination());

        return values;
    }

    private LogCursorWrapper queryActivity(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                LogDbSchema.ActivityTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new LogCursorWrapper(cursor);
    }

}
