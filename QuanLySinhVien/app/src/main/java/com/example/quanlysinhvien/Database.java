package com.example.quanlysinhvien;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qlsv.sqlite";
    private static final int DATABASE_VESTION = 1;

    //Tao bang
    public  static final String TABLE_NAME = "sinhvien";

    //Cac cot
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GT = "gioitinh";
    public static final String COLUMN_SDT = "sdt";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_LOP = "lop";

    //Kiem tra ton tai ten Database  neu chua co chay vao onCreate nguoc lai vao onUpgrade
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESTION );
    }

    @Override
    //Tao bang sinh vien
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %S TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)"
                        ,TABLE_NAME
                        ,COLUMN_ID
                        ,COLUMN_NAME
                        ,COLUMN_GT
                        ,COLUMN_SDT
                        ,COLUMN_EMAIL
                        ,COLUMN_LOP));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
