package com.example.se1606_prm392_group05.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.se1606_prm392_group05.Data.RegisterHelper;

public class UserController {
    private RegisterHelper registerHelper;

    public UserController(RegisterHelper registerHelper) {
        this.registerHelper = registerHelper;
    }

    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = registerHelper.getReadableDatabase();

        String[] columns = {RegisterHelper.COLUMN_USERNAME, RegisterHelper.COLUMN_PASSWORD};
        String selection = RegisterHelper.COLUMN_USERNAME + " = ? AND " + RegisterHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(RegisterHelper.TABLE_USER , columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        db.close();

        return count > 0;
    }
}
