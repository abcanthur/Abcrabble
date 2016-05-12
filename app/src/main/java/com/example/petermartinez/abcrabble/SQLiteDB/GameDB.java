package com.example.petermartinez.abcrabble.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;

/**
 * Created by petermartinez on 5/11/16.
 */
public class GameDB {

    private SQLiteDatabase DB;
    private GameSQLiteHelper gameSQLiteHelper;

    public GameDB(Context context) {
        gameSQLiteHelper = new GameSQLiteHelper(context);
    }



    public Cursor getAllGames() {
        Cursor cursor = DB.rawQuery("select * from " + GameSQLiteHelper.GAMES_TABLE_NAME + " ORDER BY " + GameSQLiteHelper.COL_TIME_CREATED + " DESC", null);

        return cursor;
    }

    public Cursor getGameById(String[] query){
        Cursor cursor = DB.query(GameSQLiteHelper.GAMES_TABLE_NAME, // a. table
                GameSQLiteHelper.GAMES_COLUMNS, // b. column names
                GameSQLiteHelper.COL_ID + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                GameSQLiteHelper.COL_TIME_CREATED, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor getPlayersByGame(String[] query) {
        Cursor cursor = DB.query(GameSQLiteHelper.PLAYERS_TABLE_NAME, // a. table
                GameSQLiteHelper.PLAYERS_COLUMNS, // b. column names
                GameSQLiteHelper.COL_PLAYER_GAME + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor getEventsByGame(String[] query) {
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = DB.query(GameSQLiteHelper.EVENTS_TABLE_NAME, // a. table
                GameSQLiteHelper.EVENTS_COLUMNS, // b. column names
                GameSQLiteHelper.COL_GAME + " = ?", // c. selections
                query, // d. selections args
                null, // e. group by
                null, // f. having
                GameSQLiteHelper.COL_GAME_ORDER, // g. order by
                null); // h. limit
        return cursor;
    }

    // Open Database function
    public void open() throws SQLException {
        // Allow DB to be in writable mode
        DB = gameSQLiteHelper.getWritableDatabase();
    }

    // Close Database function
    public void close() {
        if (DB != null)
            DB.close();
    }

    // emulate these functions once you need them, must insert events from record, players from setup
//    // Create Database function
//    public void InsertNote(String title, String note) {
//        ContentValues newCon = new ContentValues();
//        newCon.put(TITLE, title);
//        newCon.put(NOTE, note);
//
//        open();
//        DB.insert(TABLE_NAME, null, newCon);
//        close();
//    }
//
//    // Update Database function
//    public void UpdateNote(long id, String title, String note) {
//        ContentValues editCon = new ContentValues();
//        editCon.put(TITLE, title);
//        editCon.put(NOTE, note);
//
//        open();
//        DB.update(TABLE_NAME, editCon, ID + "=" + id, null);
//        close();
//    }
//
//    // Delete Database function
//    public void DeleteNote(long id) {
//        open();
//        DB.delete(TABLE_NAME, ID + "=" + id, null);
//        close();
//    }
//
//    // List all data function
//    public Cursor ListAllNotes() {
//        return DB.query(TABLE_NAME, new String[] { ID, TITLE }, null,
//                null, null, null, TITLE);
//    }
//
//    // Capture single data by ID
//    public Cursor GetOneNote(long id) {
//        return DB.query(TABLE_NAME, null, ID + "=" + id, null, null,
//                null, null);
//    }
}
