package com.example.se1606_prm392_group05.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegisterHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_USERNAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PHONE + " TEXT," +
            COLUMN_ADDRESS + " TEXT" +
            ")";

    public RegisterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Thêm tài khoản admin khi tạo cơ sở dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        String adminUsername = "admin";
        String adminPassword = "123";

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, adminUsername);
        contentValues.put(COLUMN_PASSWORD, adminPassword);

        db.insert(TABLE_USER, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        } else
            return false;
    }

    public Boolean updatePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDB.update("users", contentValues,"username = ?", new String[] {username});
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }
}