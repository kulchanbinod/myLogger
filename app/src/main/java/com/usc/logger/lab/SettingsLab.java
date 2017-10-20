package com.usc.logger.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.logger.database.LogDbSchema;
import com.usc.logger.models.Settings;
import com.usc.logger.cursors.SettingsCursorWrapper;
import com.usc.logger.database.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class SettingsLab {

    private static SettingsLab sSettingsLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Settings> mSettings;

    public static SettingsLab get(Context context){
        if(sSettingsLab == null){
            sSettingsLab = new SettingsLab(context);
        }
        return sSettingsLab;
    }

    private SettingsLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new LogHelper(mContext).getWritableDatabase();

    }

    public Settings getSettings(){
        List<Settings> settings = new ArrayList<>();
        SettingsCursorWrapper cursor = querySettings(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                settings.add(cursor.getSetting());
                cursor.moveToNext();
            }
        } finally {

        }

        return settings.get(0);

    }

    public void updateSettings(Settings s){

        String uuidString = s.getmId().toString();
        ContentValues values = getContentValues(s);

        mDatabase.update(LogDbSchema.SettingsTable.NAME, values, LogDbSchema.SettingsTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    private static ContentValues getContentValues(Settings settings){

        ContentValues values = new ContentValues();
        values.put(LogDbSchema.SettingsTable.Cols.ID,settings.getId());
        values.put(LogDbSchema.SettingsTable.Cols.NAME,settings.getName());
        values.put(LogDbSchema.SettingsTable.Cols.EMAIL,settings.getEmail());
        values.put(LogDbSchema.SettingsTable.Cols.GENDER,settings.getGender());
        values.put(LogDbSchema.SettingsTable.Cols.COMMENT,settings.getComment());
        values.put(LogDbSchema.SettingsTable.Cols.UUID,settings.getmId().toString());

        return values;
    }

    private SettingsCursorWrapper querySettings(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                LogDbSchema.SettingsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SettingsCursorWrapper(cursor);
    }

}
