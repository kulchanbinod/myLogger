package com.usc.logger.cursors;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.logger.database.LogDbSchema;
import com.usc.logger.models.Settings;

import java.util.UUID;


public class SettingsCursorWrapper extends CursorWrapper {

    public SettingsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Settings getSetting(){

        String id = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.ID));
        String name = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.NAME));
        String email = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.COMMENT));
        String uuidString  = getString(getColumnIndex(LogDbSchema.SettingsTable.Cols.UUID));

        Settings setting = new Settings(UUID.fromString(uuidString));
        setting.setName(name);
        setting.setId(id);
        setting.setEmail(email);
        setting.setGender(gender);
        setting.setComment(comment);
        return setting;

    }

}
