package com.example.android_logo_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String ID = "ID";
    public static final String PLAYER_NAME = "PLAYER_NAME";
    public static final String PLAYER_AGE = "PLAYER_AGE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + PLAYER_TABLE + "(" + ID + " " +
                "INTEGER PRIMARY KEY AUTOINCREMENT," + PLAYER_NAME + " TEXT, " + PLAYER_AGE + " INT)";
        db.execSQL(CreateTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+PLAYER_TABLE);
        onCreate(db);

    }
    public boolean addRecord(PlayerDetails playerDetails){
        SQLiteDatabase db = this.getWritableDatabase();
        //contentValues
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_NAME, playerDetails.getName());
        cv.put(PLAYER_AGE, playerDetails.getAge());
        long insert = db.insert(PLAYER_TABLE,null,cv );
        if(insert== -1){
            return false;
        }
        else {
            return true;
        }
    }
    public List<PlayerDetails> getalldata(){
        List<PlayerDetails> display  = new ArrayList<>();

        String query = "SELECT * FROM "+PLAYER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int playerID = c.getInt(0);
                String playerName = c.getString(1);
                int playerAge = c.getInt(2);
                PlayerDetails newplayer = new PlayerDetails(playerName,playerID,playerAge);
                display.add(newplayer);

            }while (c.moveToNext());
        }else{

        }
        c.close();
        db.close();

        return display;

    }



    public Integer deleteData(String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PLAYER_TABLE,"ID = ? " ,new String[]{id});
    }

}
