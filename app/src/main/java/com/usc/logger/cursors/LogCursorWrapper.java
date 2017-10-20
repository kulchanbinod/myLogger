package com.usc.logger.cursors;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.logger.database.LogDbSchema;
import com.usc.logger.models.Log;

import java.util.Date;
import java.util.UUID;


public class LogCursorWrapper extends CursorWrapper {

    public LogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Log getActivity(){

        long date = getLong(getColumnIndex(LogDbSchema.ActivityTable.Cols.DATE));
        String type = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.TYPE));
        String title = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.TITLE));
        String comment = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.COMMENT));
        String uuidString  = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.UUID));
        String duration = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.DURATION));
        String location = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.LOCATION));
        String destination = getString(getColumnIndex(LogDbSchema.ActivityTable.Cols.DESTINATION));

        Log activity = new Log(UUID.fromString(uuidString));
        activity.setType(type);
        activity.setTitle(title);
        activity.setComment(comment);
        activity.setDuration(duration);
        activity.setLocation(location);
        activity.setDestination(destination);
        activity.setDate(new Date(date).toString());
        return activity;

    }

}
